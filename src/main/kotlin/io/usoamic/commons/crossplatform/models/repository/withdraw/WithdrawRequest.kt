package io.usoamic.commons.crossplatform.models.repository.withdraw

import io.usoamic.usoamickt.enumcls.TxSpeed
import java.math.BigDecimal

data class WithdrawRequest(
    val password: String,
    val to: String,
    val value: BigDecimal,
    val txSpeed: TxSpeed
)