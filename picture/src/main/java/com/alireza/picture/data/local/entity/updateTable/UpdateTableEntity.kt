package com.alireza.picture.data.local.entity.updateTable

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alireza.core.data.local.entity.EntityModel
import java.util.*

@Entity(tableName = "last_update_table")
class UpdateTableEntity(val lastUpdate: Date): EntityModel {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}