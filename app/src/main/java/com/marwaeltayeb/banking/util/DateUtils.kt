package com.marwaeltayeb.banking.util

import java.text.SimpleDateFormat;
import java.util.Date;

fun formatDate(currentDate: Long): String {
    val date = Date(currentDate)
    val df2 = SimpleDateFormat("dd-MM-yyyy, h:mm a")
    return df2.format(date)
}