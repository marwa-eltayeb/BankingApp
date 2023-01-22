package com.marwaeltayeb.banking.util

import java.text.SimpleDateFormat;
import java.util.*

fun formatDate(currentDate: Long): String {
    val date = Date(currentDate)
    val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy, h:mm a", Locale.getDefault())
    return simpleDateFormat.format(date)
}