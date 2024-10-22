package com.example.banquemisrchallenge05.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object FormatDTHelper {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatReleaseDate(dateString: String): String {
        return try {
            val date = LocalDate.parse(dateString)
            val formatter = DateTimeFormatter.ofPattern("d MMM yyyy")
            date.format(formatter)
        } catch (e: DateTimeParseException) {
            dateString
        }
    }

    @SuppressLint("DefaultLocale")
    fun formatRuntimeToHMMSS(runtimeInMinutes: Int): String {
        val totalSeconds = runtimeInMinutes * 60
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        return if (hours > 0) {
            String.format("%d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }
}