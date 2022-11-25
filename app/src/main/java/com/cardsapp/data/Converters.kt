package com.cardsapp.data

import androidx.room.TypeConverter
import java.time.ZonedDateTime

class Converters {

    @TypeConverter
    fun zonedDateTimeToString(zonedDateTime: ZonedDateTime) = zonedDateTime.toString()

    @TypeConverter
    fun parseZonedDateTime(string: String): ZonedDateTime = ZonedDateTime.parse(string)

}