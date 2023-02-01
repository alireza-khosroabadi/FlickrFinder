package com.alireza.picture.presentation.searchPhoto

import com.alireza.core.presentation.fragment.BaseObserverFragment
import com.alireza.picture.databinding.FragmentSearchPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchPhotoFragment:BaseObserverFragment<FragmentSearchPhotoBinding>() {

    override fun observe() {

    }

    override fun getViewBinding(): FragmentSearchPhotoBinding =
        FragmentSearchPhotoBinding.inflate(layoutInflater)

    override fun setupViews() {

    }

    override fun donOnCreateView() {

    }
}