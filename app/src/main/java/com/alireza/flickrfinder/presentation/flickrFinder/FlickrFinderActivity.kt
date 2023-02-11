package com.alireza.flickrfinder.presentation.flickrFinder


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alireza.flickrfinder.presentation.router.FlickrFinderApp
import com.alireza.ui.theme.FlickrFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlickrFinderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrFinderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FlickrFinderApp()
                }
            }
        }
    }
}