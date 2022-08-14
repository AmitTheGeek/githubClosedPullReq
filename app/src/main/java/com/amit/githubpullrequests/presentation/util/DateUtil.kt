package com.amit.githubpullrequests.presentation.util

import java.text.SimpleDateFormat
import java.util.*


object DateUtil {
    private val outputFormat = SimpleDateFormat("dd MMM yyyy");

    fun getFormattedDate(date: Date): String {
        return outputFormat.format(date)
    }
}