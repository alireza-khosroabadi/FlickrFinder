package com.alireza.picture.data.local.entity.searchHistory

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alireza.core.data.local.entity.EntityModel
import java.util.Date

@Entity(tableName = "search_history_table")
data class SearchHistoryEntity(@PrimaryKey val query:String): EntityModel{
    var date: Date = Date()
}