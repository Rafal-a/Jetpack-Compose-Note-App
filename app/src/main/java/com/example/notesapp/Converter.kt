package com.example.notesapp

import androidx.room.TypeConverter
import java.sql.Date

class Converter {

    @TypeConverter
    fun convertToDate(date: Long)= Date(date)

    @TypeConverter
    fun convertToLong(date: Date)= date.time
}
