package io.usoamic.commons.crossplatform.models.usecases.dashboard

import java.math.BigDecimal
import java.math.BigInteger

data class DashboardModel(
    val ethBalance: BigDecimal,
    val usoBalance: BigDecimal,
    val height: BigInteger,
    val supply: BigDecimal
)