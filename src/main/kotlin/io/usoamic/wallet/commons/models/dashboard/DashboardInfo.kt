package io.usoamic.wallet.commons.models.dashboard

import java.math.BigDecimal
import java.math.BigInteger

data class DashboardInfo(
    val ethBalance: BigDecimal,
    val usoBalance: BigDecimal,
    val height: BigInteger,
    val supply: BigDecimal
) {
    companion object {
        val DEFAULT = DashboardInfo(
            BigDecimal(111),
            BigDecimal(222),
            BigInteger.valueOf(333),
            BigDecimal(444)
        )
    }
}