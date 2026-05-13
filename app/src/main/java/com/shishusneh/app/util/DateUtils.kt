package com.shishusneh.app.util

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateUtils {
    private val display = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
    private val monthShort = SimpleDateFormat("MMM", Locale.getDefault())

    fun format(millis: Long): String = display.format(Date(millis))
    fun monthLabel(millis: Long): String = monthShort.format(Date(millis))

    fun ageLabel(dobMillis: Long): String {
        val now = System.currentTimeMillis()
        val days = TimeUnit.MILLISECONDS.toDays(now - dobMillis)
        return when {
            days < 14 -> "$days days old"
            days < 60 -> "${days / 7} weeks old"
            days < 365 -> "${days / 30} months old"
            else -> {
                val years = days / 365
                val months = (days % 365) / 30
                "$years year $months months"
            }
        }
    }
}
