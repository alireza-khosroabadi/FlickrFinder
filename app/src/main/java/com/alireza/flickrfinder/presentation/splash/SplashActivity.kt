package com.alireza.flickrfinder.presentation.splash

import android.os.Build
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.alireza.core.presentation.activity.BaseActivity
import com.alireza.flickrfinder.databinding.ActivitySplashBinding
import com.alireza.flickrfinder.presentation.router.flickrFinderIntent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun doOnCreate() {
        configSystemHiddenUi()
        splashImageAnimation()
        splashTitleAnimation()
        navigateToFlickrFinderList()
    }

    private fun navigateToFlickrFinderList() {
        binding.splashScreenImage.postDelayed({
            startActivity(flickrFinderIntent(baseContext))
            finish()
        }, 2000)
    }

    private fun splashImageAnimation() {
        val slideAnimation =
            AnimationUtils.loadAnimation(this, com.alireza.core.R.anim.side_slide_start)
        binding.splashScreenImage.startAnimation(slideAnimation)
    }

    private fun splashTitleAnimation() {
        val slideAnimation =
            AnimationUtils.loadAnimation(this, com.alireza.core.R.anim.side_slide_end)
        binding.splashScreenTitle.startAnimation(slideAnimation)
    }

    private fun configSystemHiddenUi() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}