package com.alireza.flickrfinder.presentation.compuseSplash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alireza.picture.presentation.compose.recentPhoto.RecentPhotoScreen
import com.alireza.picture.presentation.screen.FlickrFinderApp
//import com.alireza.picture.presentation.screen.FlickrFinderApp
import com.alireza.ui.theme.FlickrFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrFinderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FlickrFinderApp()
//                   RecentPhotoScreen()
                }
            }
        }
    }
}