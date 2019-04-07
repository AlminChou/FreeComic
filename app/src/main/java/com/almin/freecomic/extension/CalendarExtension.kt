package com.almin.freecomic.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Almin on 2018/12/5.
 */
fun Calendar.parseTime(time: Long, format: String = "yyyy-MM-dd HH:mm:ss"): String{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time
    return SimpleDateFormat(format, Locale.US).format(calendar.time)
}