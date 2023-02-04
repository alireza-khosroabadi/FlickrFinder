package com.alireza.picture.data.local.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alireza.picture.data.fakeData.fakeRecentPhotoEntity
import com.alireza.picture.data.fakeData.fakeUnFavoritePhoto
import com.alireza.picture.data.local.dao.favoritePhoto.FavoritePhotoDao
import com.alireza.picture.data.local.dao.recentPhoto.RecentPhotoDao
import com.alireza.picture.data.local.dao.updateTable.UpdateTableDao
import com.alireza.picture.data.local.entity.updateTable.UpdateTableEntity
import com.alireza.picture.data.param.favoritePhoto.FavoritePhotoParam
import com.alireza.picture.data.param.favoritePhoto.toPhotoDetailEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*
import java.util.concurrent.Executors

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class PhotoDataBaseTest {

    private lateinit var updateTableDao: UpdateTableDao
    private lateinit var favoriteDao: FavoritePhotoDao
    private lateinit var recentPhotoDao: RecentPhotoDao
    private lateinit var dataBase: PhotoDataBase


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dataBase = Room.inMemoryDatabaseBuilder(context, PhotoDataBase::class.java)
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()
        updateTableDao = dataBase.updateTableDao()
        favoriteDao = dataBase.favoritePhotoDao()
        recentPhotoDao = dataBase.recentPhotoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        dataBase.close()
    }


    @Test
    @Throws(Exception::class)
    fun insert_new_date_and_read_it_after_insert(){
        val date = Date()
        val updateEntity = UpdateTableEntity(date)

        updateTableDao.insert(updateEntity)
        val lastUpdate = updateTableDao.lastUpdateTime()
        assertEquals(date.time, lastUpdate?.lastUpdate?.time)
    }

    @Test
    @Throws(Exception::class)
    fun return_last_updated_time_when_multiple_item_exist_in_table() {
        val date = Date()
        val updateOneEntity = UpdateTableEntity(date)
        val updateTwoEntity = UpdateTableEntity(Date(date.time+1_000_000))
        updateTableDao.insert(updateOneEntity)
        updateTableDao.insert(updateTwoEntity)

        val lastUpdate = updateTableDao.lastUpdateTime()
        assertEquals(updateTwoEntity.lastUpdate.time, lastUpdate?.lastUpdate?.time)
    }

    @Test
    @Throws(Exception::class)
    fun return_null_when_table_is_empty() {
        val lastUpdate = updateTableDao.lastUpdateTime()
        assertEquals(null,lastUpdate)
    }


    @Test
    @Throws(Exception::class)
    fun insert_new_favorite_photo_and_read_it_after_insert() = runTest{
        val photo = fakeUnFavoritePhoto
        favoriteDao.insert(photo.toPhotoDetailEntity())
        val fav = favoriteDao.favoritePhotoList().take(1).toList()[0]
        assertEquals(photo.id, fav[0].id)
    }

    @Test
    @Throws(Exception::class)
    fun insert_new_favorite_photo_and_read_it_id_after_insert() = runTest{
        val photo = fakeUnFavoritePhoto
        favoriteDao.insert(photo.toPhotoDetailEntity())
        val fav = favoriteDao.favoritePhotoIdList().take(1).toList()[0]
        assertEquals(photo.id, fav[0].id)
    }

    @Test
    @Throws(Exception::class)
    fun insert_new_favorite_photo_and_delete_it_after_insert() = runTest{
        val photo = fakeUnFavoritePhoto
        favoriteDao.insert(photo.toPhotoDetailEntity())
        favoriteDao.delete(photo.toPhotoDetailEntity())
        val fav = favoriteDao.favoritePhotoIdList().take(1).toList()[0]
        assertEquals(0, fav.size)
    }


    @Test
    @Throws(Exception::class)
    fun insert_new_recent_photo_and_read_it_id_after_insert() = runTest{
        val photo = fakeRecentPhotoEntity
        recentPhotoDao.insert(photo)
        val fav = recentPhotoDao.recentPhotos().take(1).toList()[0]
        assertEquals(photo.owner, fav[0].owner)
    }

}