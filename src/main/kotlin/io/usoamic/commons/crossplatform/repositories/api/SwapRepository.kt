package io.usoamic.commons.crossplatform.repositories.api

import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.repository.swap.BurnSwapRequest
import java.math.BigDecimal

interface SwapRepository {
    val swapBalance: Single<BigDecimal>
    val swapRate: Single<BigDecimal>
    val isSwappable: Single<Boolean>

    fun burnSwap(
        data: BurnSwapRequest
    ): Single<String>
}