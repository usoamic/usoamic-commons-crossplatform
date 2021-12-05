package io.usoamic.commons.crossplatform.repositories.impl

import io.reactivex.Single
import io.usoamic.commons.crossplatform.extensions.addDebugDelay
import io.usoamic.commons.crossplatform.mappers.entity.toEther
import io.usoamic.commons.crossplatform.models.repository.swap.BurnSwapRequest
import io.usoamic.commons.crossplatform.repositories.api.SwapRepository
import io.usoamic.usoamickt.core.Usoamic
import java.math.BigDecimal
import javax.inject.Inject

class SwapRepositoryImpl @Inject constructor(
    private val usoamic: Usoamic
) : SwapRepository {
    override val swapBalance: Single<BigDecimal>
        get() = Single.fromCallable {
            usoamic.getSwapBalance().toEther()
        }.addDebugDelay()

    override val swapRate: Single<BigDecimal>
        get() = Single.fromCallable {
            usoamic.getSwapRate().toEther()
        }.addDebugDelay()

    override val isSwappable: Single<Boolean>
        get() = Single.fromCallable {
            usoamic.getSwappable() ?: false
        }

    override fun burnSwap(data: BurnSwapRequest): Single<String> {
        return Single.fromCallable {
            usoamic.burnSwap(
                password = data.password,
                value = data.value,
                txSpeed = data.txSpeed
            )
        }.addDebugDelay()
    }
}