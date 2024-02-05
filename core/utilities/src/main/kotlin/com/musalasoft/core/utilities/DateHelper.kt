package com.musalasoft.core.utilities

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale

class DateHelper constructor(private val locale: String) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormattedDate(
        dateString: String,
        pattern: String,
        format: String
    ): String {
        return runCatching {
            val serverDateFormat = getFormatter(
                pattern = pattern,
                locale = locale
            )
            val localDate = LocalDate.parse(dateString, serverDateFormat)
            val formatter = DateTimeFormatter.ofPattern(format, Locale.forLanguageTag(locale))
            localDate.format(formatter)
        }.getOrDefault(dateString)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormattedTime(
        timeString: String,
        pattern: String,
        format: String
    ): String {
        return runCatching {
            val serverDateFormat = getFormatter(
                pattern = pattern,
                locale = locale
            )
            val localTime = LocalTime.parse(timeString, serverDateFormat)
                .atOffset(ZoneOffset.UTC)
            val formatter = DateTimeFormatter.ofPattern(format, Locale.forLanguageTag(locale))
                .withZone(ZoneId.systemDefault())
            localTime.format(formatter)
        }.getOrDefault(timeString)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormattedDateTime(
        dateTimeString: String,
        pattern: String,
        format: String
    ): String {
        return runCatching {
            val serverDateFormat = getFormatter(
                pattern = pattern,
                locale = locale
            )
            val localDateTime = LocalDateTime.parse(dateTimeString, serverDateFormat)
                .atZone(ZoneId.of("UTC"))
            val formatter = DateTimeFormatter.ofPattern(format, Locale.forLanguageTag(locale))
                .withZone(ZoneId.systemDefault())
            localDateTime.format(formatter)
        }.getOrDefault(dateTimeString)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getFormatter(
        pattern: String,
        locale: String = "en"
    ): DateTimeFormatter {
        val builder = DateTimeFormatterBuilder()
        return builder.appendPattern(pattern)
            .toFormatter(Locale(locale))
    }

    val SERVER_DATE_FORMAT = "yyyy-MM-dd"
    val SERVER_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS[xxx][xx][X]"
}
