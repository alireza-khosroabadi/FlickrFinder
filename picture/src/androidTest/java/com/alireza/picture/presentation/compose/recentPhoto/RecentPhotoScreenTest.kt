package com.alireza.picture.presentation.compose.recentPhoto

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alireza.picture.data.fakeData.fakeRecentPhotoEntityList
import com.alireza.picture.data.fakeData.fakeRecentPhotoList
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecentPhotoScreenTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun recentPhotoListDisplayed(){
        composeTestRule.setContent {
            RecentPhotoScreen(uiState = RecentPhotoList(fakeRecentPhotoList), onNavigateToSearch = { }) {

            }
        }
        composeTestRule.onNodeWithTag("recentPhotoList").assertIsDisplayed()
    }


    @Test
    fun recentPhotoListLoading(){
        composeTestRule.setContent {
            RecentPhotoScreen(uiState = Loading, onNavigateToSearch = {}) {

            }
        }
        composeTestRule.onNodeWithTag("loading_progress").assertIsDisplayed()
    }
}