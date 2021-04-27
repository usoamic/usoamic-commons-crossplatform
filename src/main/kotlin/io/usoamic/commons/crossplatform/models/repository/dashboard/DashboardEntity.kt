package io.usoamic.commons.crossplatform.models.repository.dashboard

import java.math.BigDecimal
import java.math.BigInteger

data class DashboardEntity(
    val ethBalance: BigDecimal,
    val usoBalance: BigDecimal,
    val height: BigInteger,
    val supply: BigDecimal
) {
    companion object {
        val DEFAULT = DashboardEntity(
            BigDecimal(111),
            BigDecimal(222),
            BigInteger.valueOf(333),
            BigDecimal(444)
        )
    }
}