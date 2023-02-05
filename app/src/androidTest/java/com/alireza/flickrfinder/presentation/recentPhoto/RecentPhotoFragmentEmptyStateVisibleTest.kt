package com.alireza.flickrfinder.presentation.recentPhoto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.alireza.flickrfinder.config.launchFragmentInHiltContainer
import com.alireza.flickrfinder.config.waitViewShown
import com.alireza.flickrfinder.fakeData.fakeHiltModule.EmptyResponseApiService
import com.alireza.picture.R
import com.alireza.picture.data.remote.api.PictureApiService
import com.alireza.picture.di.ApiServiceModule
import com.alireza.picture.di.DataBaseModule
import com.alireza.picture.presentation.recentPhoto.RecentPhotoFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(ApiServiceModule::class, DataBaseModule::class)
class RecentPhotoFragmentEmptyStateVisibleTest {

    @Module
    @InstallIn(SingletonComponent::class)
    object EmptyResponseApiServiceModule {

        @Provides
        fun provideEmptyResponseApiService(): PictureApiService = EmptyResponseApiService()
    }

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun injectApiService() {
        hiltAndroidRule.inject()
    }

    @Test
    fun show_empty_state_view_when_photo_list_is_empty() {
        launchFragmentInHiltContainer<RecentPhotoFragment>()
        waitViewShown(withId(R.id.empty_state))
        onView(withId(R.id.empty_state)).check(matches(isDisplayed()))

    }


}