package io.usoamic.commons.crossplatform.extensions

import io.usoamic.usoamickt.util.Coin
import java.math.BigDecimal
import java.math.BigInteger
import java.text.NumberFormat
import java.util.*

private val formatter = NumberFormat.getInstance(Locale.US)

fun BigInteger?.orZero(): BigInteger = this ?: BigInteger.ZERO

fun Coin.toBeautyString(appendix: String = ""): String {
    return toBigDecimal().toBeautyString(appendix)
}

fun BigDecimal.toBeautyString(appendix: String = ""): String {
    val value = formatter.format(this)

    return buildString {
        append(value)
        if (appendix.isNotEmpty()) {
            append(" ")
            append(appendix)
        }
    }
}

fun BigInteger.toBeautyString(appendix: String = ""): String {
    return buildString {
        append(formatter.format(this@toBeautyString))
        if (appendix.isNotEmpty()) {
            append(" ")
            append(appendix)
        }
    }
}