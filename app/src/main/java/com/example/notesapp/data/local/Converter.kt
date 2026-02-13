package com.example.notesapp.data.local

import androidx.room.TypeConverter
import java.sql.Date

class Converter {

    @TypeConverter
    fun fromLongToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun fromDateToLong(date: Date?): Long? {
        return date?.time
    }
}