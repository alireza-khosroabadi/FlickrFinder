package com.alireza.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update
import com.alireza.core.data.local.entity.EntityModel

/**
 * Base Dao is a base interface for Daos to prevent boilerplate codes
 * implement base CRUD queries
* */
@Dao
interface BaseDao<T: EntityModel> {

    @Insert(onConflict = REPLACE)
    fun insert(entity: T)

    @Insert(onConflict = REPLACE)
    fun insert(entities: List<T>)

    @Update
    fun update(entity: T)

    @Delete
    fun delete(entity: T)
}