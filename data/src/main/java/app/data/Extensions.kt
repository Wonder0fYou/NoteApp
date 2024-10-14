package app.data

import java.util.Date

const val MILLISECONDS = 1000

fun Date.toUnixTimestamp(): Long {
    return this.time / MILLISECONDS
}

fun Long.toDateFromUnixTimestamp(): Date {
    return Date(this * MILLISECONDS)
}