package com.jaroid.counter.utils

import java.text.SimpleDateFormat
import java.util.*

class StringUtils {
    companion object {
        @JvmStatic fun convertToDate(time: Long): String {
            val simpleDateFormat = SimpleDateFormat("MM/dd/yyyy")
            val date = Date(time)
            return simpleDateFormat.format(date)
        }
    }
}