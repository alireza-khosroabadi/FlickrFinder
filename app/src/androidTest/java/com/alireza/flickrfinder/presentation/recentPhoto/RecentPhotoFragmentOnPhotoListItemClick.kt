package com.alireza.flickrfinder.presentation.recentPhoto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alireza.flickrfinder.config.launchFragmentInHiltContainer
import com.alireza.flickrfinder.fakeData.fakeHiltModule.EmptyResponseApiService
import com.alireza.flickrfinder.fakeData.fakeHiltModule.FakeRecentPhotoRepository
import com.alireza.flickrfinder.fakeData.fakeHiltModule.ResponseApiService
import com.alireza.picture.R
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.di.ApiServiceModule
import com.alireza.picture.di.DataBaseModule
import com.alireza.picture.di.RepositoryModule
import com.alireza.picture.domain.repository.recentPhoto.RecentPhotoRepository
import com.alireza.picture.presentation.recentPhoto.RecentPhotoFragment
import com.alireza.picture.presentation.recentPhoto.RecentPhotoListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(ApiServiceModule::class, DataBaseModule::class)
class RecentPhotoFragmentOnPhotoListItemClick {

    private val navHostController: TestNavHostController by lazy {
        TestNavHostController(ApplicationProvider.getApplicationContext())
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object SingleResponseApiServiceModule {

        @Provides
        fun provideSingleResponseApiService(): PictureApiService = ResponseApiService()
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
    fun on_recentPhoto_recyclerView_item_click() {
        launchFragmentInHiltContainer<RecentPhotoFragment> {
            navHostController.setGraph(R.navigation.flickr_picture_nav_graph)
            Navigation.setViewNavController(this.requireView(), navHostController)
        }

        onView(withId(R.id.rv_photo_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecentPhotoListAdapter.ViewHolder>(
                    0,
                    click()
                )
            )
        Assert.assertEquals(R.id.photoDetailFragment, navHostController.currentDestination?.id)
    }

}