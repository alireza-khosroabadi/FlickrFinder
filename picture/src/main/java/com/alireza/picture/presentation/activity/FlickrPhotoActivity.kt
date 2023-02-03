package com.alireza.picture.presentation.activity

import com.alireza.core.presentation.activity.BaseActivity
import com.alireza.picture.databinding.ActivityFlickrPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlickrPhotoActivity : BaseActivity<ActivityFlickrPhotoBinding>() {
    override fun getViewBinding(): ActivityFlickrPhotoBinding =
        ActivityFlickrPhotoBinding.inflate(layoutInflater)

    override fun doOnCreate() {
        /*NO OP*/
    }
}