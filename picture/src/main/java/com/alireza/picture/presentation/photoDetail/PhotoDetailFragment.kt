package com.alireza.picture.presentation.photoDetail

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.alireza.core.extentions.loadImage
import com.alireza.core.presentation.fragment.BaseObserverFragment
import com.alireza.picture.databinding.FragmentPhotoDetailBinding
import com.alireza.picture.domain.model.photoDetail.PhotoDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoDetailFragment : BaseObserverFragment<FragmentPhotoDetailBinding>() {

    private val args: PhotoDetailFragmentArgs by navArgs()
    private val mViewModel: PhotoDetailViewModel by viewModels()

    override fun donOnCreateView() {
        args.photoId
    }

    override fun getViewBinding(): FragmentPhotoDetailBinding =
        FragmentPhotoDetailBinding.inflate(layoutInflater)

    override fun setupViews() {

    }

    override fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.photoState.collect { state ->
                    when (state) {
                        Loading -> Unit
                        is PhotoState -> showPhoto(state.data)
                    }
                }
            }
        }
    }

    private fun showPhoto(data: PhotoDetail) {
        binding.imgPhoto.loadImage(data.url)
    }
}