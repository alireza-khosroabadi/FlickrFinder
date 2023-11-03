package com.alireza.picture.presentation.favoritePhoto.remove

import androidx.fragment.app.viewModels
import com.alireza.core.presentation.bottomSheet.BaseBottomSheet
import com.alireza.core.presentation.bottomSheet.BaseObserverBottomSheet
import com.alireza.picture.databinding.BottomSheetRemoveFavoritePhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RemoveFavoritePhotoBottomSheet :
    BaseBottomSheet<BottomSheetRemoveFavoritePhotoBinding>() {

    private val mViewModel: RemoveFavoritePhotoViewModel by viewModels()

    override fun getViewBinding(): BottomSheetRemoveFavoritePhotoBinding =
        BottomSheetRemoveFavoritePhotoBinding.inflate(layoutInflater)

    override fun setupViews() {
        binding.btnYes.setOnClickListener {
            mViewModel.removePhoto()
            dismiss()
        }


        binding.btnNo.setOnClickListener {
            dismiss()
        }

    }

}