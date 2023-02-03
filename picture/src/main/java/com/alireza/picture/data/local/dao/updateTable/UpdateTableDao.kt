package com.alireza.picture.data.local.dao.updateTable

import androidx.room.Dao
import androidx.room.Query
import com.alireza.core.data.local.dao.BaseDao
import com.alireza.picture.data.local.entity.updateTable.UpdateTableEntity
import java.util.*

@Dao
interface UpdateTableDao : BaseDao<UpdateTableEntity> {

    @Query("SELECT * FROM LAST_UPDATE_TABLE ORDER BY lastUpdate DESC LIMIT 1")
    fun lastUpdateTime(): UpdateTableEntity?


}