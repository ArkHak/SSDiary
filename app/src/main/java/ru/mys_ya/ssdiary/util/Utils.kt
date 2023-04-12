package ru.mys_ya.ssdiary.util

import java.text.SimpleDateFormat
import java.util.*

private fun convert(date: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(date)
    val timestamp = date.time
    return getDateStringFromString(timestamp.toString())
}

fun getDateStringFromString(dateTimeString: String): String {
    var dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(dateTimeString)
    dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return (date.time / 1000).toString()
}