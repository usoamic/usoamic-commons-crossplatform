package io.usoamic.wallet.commons.extensions

import io.usoamic.usoamickt.util.Coin
import java.math.BigDecimal
import java.math.BigInteger

fun BigInteger?.orZero(): BigInteger = this ?: BigInteger.ZERO

fun Coin.toBeautyString(appendix: String = ""): String {
    return toBigDecimal().toBeautyString(appendix)
}

fun BigDecimal.toBeautyString(appendix: String = ""): String {
    return buildString {
        append(this@toBeautyString.stripTrailingZeros().toPlainString())
        append(" ")
        append(appendix)
    }
}

fun BigInteger.toBeautyString(appendix: String = ""): String {
    return buildString {
        append(this@toBeautyString.toString())
        append(appendix)
    }
}