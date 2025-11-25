package com.neonscan.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.simpleDate(): String =
    SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(this))
