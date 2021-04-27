package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.mappers.local.toItem
import io.usoamic.commons.crossplatform.models.repository.dashboard.DashboardEntity
import io.usoamic.commons.crossplatform.models.usecases.dashboard.DashboardModel
import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.commons.crossplatform.repositories.api.EthereumRepository
import io.usoamic.commons.crossplatform.repositories.api.TokenRepository
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

class DashboardUseCases @Inject constructor(
    private val mTokenRepository: TokenRepository,
    private val mEthereumRepository: EthereumRepository,
    private val mDbRepository: DbRepository
) {
    fun getDashboardInfo(forceUpdate: Boolean): Single<DashboardModel> {
        return if(forceUpdate) {
            getDashboardInfoFromNetwork()
        }
        else {
            getDashboardInfoFromCache()
        }
    }

    private fun getDashboardInfoFromCache(): Single<DashboardModel> {
        return mDbRepository.getDashboardInfo()?.let {
            Single.just(it.toItem())
        } ?: getDashboardInfoFromNetwork()
    }

    private fun getDashboardInfoFromNetwork(): Single<DashboardModel> {
        return Single.zip(
            mEthereumRepository.ethBalance,
            mTokenRepository.usoBalance,
            mEthereumRepository.ethHeight,
            mTokenRepository.usoSupply
        ) { ethBalance: BigDecimal, usoBalance: BigDecimal, ethHeight: BigInteger, usoSupply: BigDecimal ->
            DashboardEntity(
                ethBalance,
                usoBalance,
                ethHeight,
                usoSupply
            )
        }
            .map {
                mDbRepository.updateDashboardInfo(it)
                it.toItem()
            }
    }
}