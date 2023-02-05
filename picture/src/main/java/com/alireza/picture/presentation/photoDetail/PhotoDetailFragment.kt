package com.alireza.picture.presentation.photoDetail

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alireza.core.data.error.AppError
import com.alireza.core.extentions.loadImage
import com.alireza.core.presentation.fragment.BaseObserverFragment
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.core.presentation.viewModel.ExceptionState
import com.alireza.picture.databinding.FragmentPhotoDetailBinding
import com.alireza.picture.domain.model.photoDetail.PhotoDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoDetailFragment : BaseObserverFragment<FragmentPhotoDetailBinding>() {

    private val args: PhotoDetailFragmentArgs by navArgs()
    private val mViewModel: PhotoDetailViewModel by viewModels()

    override fun donOnCreateView() {
        setupOnBackClickListener()
        setupFavoriteClickListener()
        errorStateListener()
    }

    override fun getViewBinding(): FragmentPhotoDetailBinding =
        FragmentPhotoDetailBinding.inflate(layoutInflater)

    override fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    observePhotoState()
                }
                launch {
                    observeErrorState()
                }
            }
        }
    }

    private suspend fun observeErrorState() {
        mViewModel.errorState.collect { error ->
            when (error) {
                is ErrorState -> showError(error.message)
                is ExceptionState -> showError(error.error)
            }
        }
    }

    private suspend fun observePhotoState() {
        mViewModel.photoState.collect { state ->
            when (state) {
                Loading -> Unit
                is PhotoState -> showPhoto(state.data)
                is IsFavoritePhoto -> setFavoriteIcon(state.isFavorite)
            }
        }
    }

    private fun showPhoto(data: PhotoDetail) {
        showLoading(false)
        with(binding) {
            imgPhoto.loadImage(data.url)
            tvViewCount.text = data.views.toString()
            tvCommentCount.text = data.commentsCount.toString()
            tvOwner.text = data.ownerName
            tvDate.text = data.postedDate
            tvLocation.text = data.location
            tvDescription.text = data.description
            tvTitle.text = data.title
            setFavoriteIcon(data.isFavorite)
        }
    }

    private fun setFavoriteIcon(isFavorite: Boolean) {
        val favoriteIcon =
            if (isFavorite) com.alireza.core.R.drawable.ic_favorite
            else com.alireza.core.R.drawable.ic_no_favorite
        binding.isFavorite.setImageResource(favoriteIcon)
    }

    private fun setupOnBackClickListener() {
        binding.iconBack.setOnClickListener { findNavController().navigateUp() }
    }

    private fun showLoading(loading: Boolean) {
        binding.progressLoading.isVisible = loading
        binding.detailContainer.isVisible = true
    }

    private fun setupFavoriteClickListener() {
        binding.isFavorite.setOnClickListener {
            mViewModel.favoritePhoto()
        }
    }

    private fun showError(data: AppError) {
        showLoading(false)
        binding.errorState.isVisible = true
        binding.errorState.setAppError(data)
        binding.detailContainer.isVisible = false
    }

    private fun showError(message: String) {
        showLoading(false)
        binding.errorState.isVisible = true
        binding.errorState.setCaption(message)
        binding.detailContainer.isVisible = false
    }

    private fun errorStateListener() {
        binding.errorState.setOnButtonClickListener {
            findNavController().navigateUp()
        }
    }

    override fun setupViews() {
        /*NO OP*/
    }
}