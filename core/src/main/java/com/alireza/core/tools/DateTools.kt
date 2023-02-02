package com.alireza.core.tools

import java.util.*


/**
 * calculate days before today
 * @param daysAgo is number days before today
 **/
fun getDaysAgo(daysAgo: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
    return calendar.time
}