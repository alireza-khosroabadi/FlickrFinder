package com.alireza.picture.presentation.favoritePhoto

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alireza.core.extentions.safeNavigation
import com.alireza.core.presentation.fragment.BaseObserverFragment
import com.alireza.picture.R
import com.alireza.picture.databinding.FragmentFavoritePhotoBinding
import com.alireza.picture.domain.model.photoDetail.PhotoDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritePhotoFragment : BaseObserverFragment<FragmentFavoritePhotoBinding>() {

    private val mViewModel: FavoritePhotoViewModel by viewModels()
    private val favoritePhotoListAdapter: FavoritePhotoListAdapter by lazy { FavoritePhotoListAdapter() }

    override fun getViewBinding(): FragmentFavoritePhotoBinding =
        FragmentFavoritePhotoBinding.inflate(layoutInflater)

    override fun donOnCreateView() {
        emptyStateListener()
        favoriteListAdapterClickListener()
        favoriteListAdapterLongClickListener()
        setupOnBackClickListener()
    }

    override fun setupViews() {
        initializeRecyclerView()
    }

    override fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                mViewModel.favoritePhotoState.collect { state ->
                    when (state) {
                        is FavoritePhotoList -> showFavoritePhotos(state.data)
                        Loading -> showLoading(true)
                    }
                }
            }
        }
    }

    private fun showFavoritePhotos(data: List<PhotoDetail>) {
        showLoading(false)
        binding.emptyState.isVisible = data.isEmpty()
        binding.rvFavoritePhoto.isVisible = binding.emptyState.isVisible.not()
        favoritePhotoListAdapter.addFavoritePhoto(data)
    }


    private fun initializeRecyclerView() {
        with(binding.rvFavoritePhoto) {
            layoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            adapter = favoritePhotoListAdapter
        }
    }


    private fun showLoading(loading: Boolean) {
        binding.progressLoading.isVisible = loading
        binding.emptyState.isVisible = false
    }

    private fun favoriteListAdapterClickListener() {
        favoritePhotoListAdapter.onPhotoClick = { photoId, photoUrl ->
            FavoritePhotoFragmentDirections.actionFavoritePhotoFragmentToPhotoDetailFragment(
                photoId,
                photoUrl
            ).also { action ->
                safeNavigation(action, R.id.favoritePhotoFragment)
            }
        }
    }

    private fun favoriteListAdapterLongClickListener() {
        favoritePhotoListAdapter.onPhotoLongClick = { photoId ->
            FavoritePhotoFragmentDirections.actionFavoritePhotoFragmentToRemoveFavoritePhotoBottomSheet(
                photoId
            ).also { action ->
                safeNavigation(action, R.id.favoritePhotoFragment)
            }
        }
    }

    private fun emptyStateListener() {
        binding.emptyState.setOnButtonClickListener {
            mViewModel.loadFavoritePhoto()
        }
    }

    private fun setupOnBackClickListener() {
        binding.iconBack.setOnClickListener { findNavController().navigateUp() }
    }

}