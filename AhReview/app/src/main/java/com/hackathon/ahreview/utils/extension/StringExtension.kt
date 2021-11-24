package kr.hs.dgsw.smartschool.morammoram.presentation.extension

import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

fun String.yearFormat(): String {
    val beforeFormat = SimpleDateFormat("yyyy-M-d", Locale.getDefault())
    val afterFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    val date = beforeFormat.parse(this)!!
    return afterFormat.format(date)
}

fun String.yearDate(): Date {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return format.parse(this)!!
}

fun String.timeDate(): Date {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return format.parse(this)!!
}

fun String.getTime(): Date {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.parse(this)!!
}

fun String.isNotEmailValid(): Boolean {
    return !(!TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches())
}

fun String.isNotPhoneNumberValid(): Boolean {
    if(!TextUtils.isEmpty(this) && !Pattern.matches("[a-zA-Z]+", this)) return this.length != 11
    return true
}