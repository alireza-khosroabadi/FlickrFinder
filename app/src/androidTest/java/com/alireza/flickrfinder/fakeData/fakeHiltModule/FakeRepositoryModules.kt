package com.alireza.flickrfinder.fakeData.fakeHiltModule

import com.alireza.core.data.repository.DataModel
import com.alireza.flickrfinder.fakeData.fakeDataModel.fakeRecentPhotoDataModelFlow
import com.alireza.picture.data.local.entity.recentPhoto.RecentPhotoEntity
import com.alireza.picture.data.param.shouldFetch.ShouldFetchParam
import com.alireza.picture.domain.repository.recentPhoto.RecentPhotoRepository
import kotlinx.coroutines.flow.Flow

class FakeRecentPhotoRepository : RecentPhotoRepository {
    override fun recentPhoto(forceToFetch: ShouldFetchParam): Flow<DataModel<List<RecentPhotoEntity>>> =
        fakeRecentPhotoDataModelFlow

}

