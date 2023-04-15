package ru.mys_ya.ssdiary.util

import java.text.SimpleDateFormat
import java.util.*

const val SECONDS_IN_DAY = 86399L
const val TIME_ADJUSTMENT = 1000L
fun convertDate(date: String): Long {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(date)
    val timestamp = date.time
    val dateFormatt = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return convertDateToTimestamp(timestamp.toString())
}

fun convertTimestampToTime(timestamp: Long): String {
    val date = Date(timestamp * TIME_ADJUSTMENT)
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return formatter.format(date)
}

fun convertDateToTimestamp(dateTimeString: String): Long {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(dateTimeString)
    return if (date != null) {
        (date.time / TIME_ADJUSTMENT)
    } else {
        TIME_ADJUSTMENT
    }
}