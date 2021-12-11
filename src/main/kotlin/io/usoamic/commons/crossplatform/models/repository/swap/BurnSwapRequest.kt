package io.usoamic.commons.crossplatform.models.repository.swap

import io.usoamic.usoamickt.enumcls.TxSpeed
import java.math.BigInteger

data class BurnSwapRequest(
    val password: String,
    val value: BigInteger,
    val txSpeed: TxSpeed = TxSpeed.Auto
)