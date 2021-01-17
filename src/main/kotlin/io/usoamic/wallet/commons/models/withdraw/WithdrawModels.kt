package io.usoamic.wallet.commons.models.withdraw

import io.usoamic.usoamickt.enumcls.TxSpeed
import java.math.BigDecimal

data class WithdrawData(
    val password: String,
    val to: String,
    val value: BigDecimal,
    val txSpeed: TxSpeed
)

enum class WithdrawCoin {
    ETH,
    USO
}