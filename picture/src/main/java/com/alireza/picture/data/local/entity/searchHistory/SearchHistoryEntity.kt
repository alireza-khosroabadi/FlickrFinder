package com.alireza.picture.data.local.entity.searchHistory

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alireza.core.data.local.entity.EntityModel

@Entity(tableName = "search_history_table")
data class SearchHistoryEntity(val query:String): EntityModel {
    @PrimaryKey(autoGenerate = true) var _id:Long=0L
}