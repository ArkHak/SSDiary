package ru.mys_ya.ssdiary.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

const val SECONDS_IN_DAY = 86399L
const val TIME_ADJUSTMENT = 1000L

fun convertTimestampToTime(timestamp: Long): String {
    val date = Date(timestamp * TIME_ADJUSTMENT)
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return formatter.format(date)
}

fun convertTimestampToDateTime(timestamp: Long): String {
    val date = Date(timestamp * TIME_ADJUSTMENT)
    val formatter = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.getDefault())
    return formatter.format(date)
}

fun convertTimestampToHourTime(timestamp: Long): String {
    val date = Date(timestamp * TIME_ADJUSTMENT)
    val formatter = SimpleDateFormat("H", Locale.getDefault())
    return formatter.format(date)
}

fun convertDateToTimestamp(dateString: String): Long {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(dateString)
    return if (date != null) {
        (date.time / TIME_ADJUSTMENT)
    } else {
        TIME_ADJUSTMENT
    }
}

fun convertSystemLocalTimeDateToTimestamp(localTime: LocalTime, localDate: LocalDate): Long {
    val localDateTime = LocalDateTime.of(localDate, localTime)
    val zoneId = ZoneId.systemDefault()
    val timestamp = localDateTime.atZone(zoneId).toInstant().toEpochMilli()
    return timestamp / TIME_ADJUSTMENT
}