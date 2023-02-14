package com.alireza.flickrfinder.presentation.router

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alireza.picture.presentation.compose.recentPhoto.RecentPhotoScreen
import com.alireza.picture.presentation.compose.recentPhoto.RecentPhotoViewModel
import com.alireza.picture.presentation.compose.searchPhoto.SearchPhotoScreen

enum class FlickrFinderScreen {
    RecentPhotoScreen, SearchPhotoScreen, DetailPhotoScreen, FavoritePhotoScreen
}


@Composable
fun FlickrFinderApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = FlickrFinderScreen.RecentPhotoScreen.name
    ) {
        composable(FlickrFinderScreen.RecentPhotoScreen.name) {
            RecentPhotoScreen(
                onNavigateToSearch = {
                    navController.navigate(FlickrFinderScreen.SearchPhotoScreen.name)
                }, onNavigateFavorite = {
//                navController.navigate(FlickrFinderScreen.RecentPhotoScreen.name)
                })
        }
        composable(FlickrFinderScreen.SearchPhotoScreen.name) {
            SearchPhotoScreen(){photoId, photoUrl ->  }
        }
    }
}