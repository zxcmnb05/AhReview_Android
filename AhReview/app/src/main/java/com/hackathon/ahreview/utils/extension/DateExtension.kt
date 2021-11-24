package kr.hs.dgsw.smartschool.morammoram.presentation.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.yearFormat(): String {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return format.format(this)
}

fun Date.dateFormat(): String {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return format.format(this)
}

fun Date.timeFormat(): Date {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.parse(format.format(this))!!
}