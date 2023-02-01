package com.alireza.core.data.local.converter

import androidx.room.TypeConverter
import java.util.*

/**
 * convert date to long and vice versa to insert date to database
 * */
class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}