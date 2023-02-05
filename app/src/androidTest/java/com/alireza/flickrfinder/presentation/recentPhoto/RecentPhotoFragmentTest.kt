package com.alireza.flickrfinder.presentation.recentPhoto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alireza.flickrfinder.config.launchFragmentInHiltContainer
import com.alireza.picture.R
import com.alireza.picture.di.DataBaseModule
import com.alireza.picture.presentation.recentPhoto.RecentPhotoFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@UninstallModules(DataBaseModule::class)
@RunWith(AndroidJUnit4::class)
class RecentPhotoFragmentTest{

    private val navController: TestNavHostController by lazy {
        TestNavHostController(ApplicationProvider.getApplicationContext())
    }


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)


    @Test
    fun progress_loading_is_visible_at_first(){
        launchFragmentInHiltContainer<RecentPhotoFragment>()
        onView(withId(R.id.progress_loading)).check(matches(isDisplayed()))
    }

    @Test
    fun recycler_view_is_visible_after_loading(){
        launchFragmentInHiltContainer<RecentPhotoFragment>()
        onView(withId(R.id.rv_photo_list)).check(matches(isDisplayed()))
    }

    @Test
    fun navigate_to_search_photo_fragment(){
        launchFragmentInHiltContainer<RecentPhotoFragment> {
            navController.setGraph(R.navigation.flickr_picture_nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
        }

        onView(withId(R.id.img_search)).perform(click())
        assertEquals(R.id.searchPhotoFragment, navController.currentDestination?.id)
    }

    @Test
    fun navigate_to_favorite_photo_fragment(){
        launchFragmentInHiltContainer<RecentPhotoFragment> {
            navController.setGraph(R.navigation.flickr_picture_nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
        }

        onView(withId(R.id.img_favorite)).perform(click())
        assertEquals(R.id.favoritePhotoFragment, navController.currentDestination?.id)
    }

}