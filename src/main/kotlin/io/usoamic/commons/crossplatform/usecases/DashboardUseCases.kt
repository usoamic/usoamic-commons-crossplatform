package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.dashboard.DashboardInfo
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
    fun getDashboardInfo(forceUpdate: Boolean): Single<DashboardInfo> {
        return if(forceUpdate) {
            getDashboardInfoFromNetwork()
        }
        else {
            getDashboardInfoFromCache()
        }
    }

    private fun getDashboardInfoFromCache(): Single<DashboardInfo> {
        return mDbRepository.getDashboardInfo()?.let {
            Single.just(it)
        } ?: getDashboardInfoFromNetwork()
    }

    private fun getDashboardInfoFromNetwork(): Single<DashboardInfo> {
        return Single.zip(
            mEthereumRepository.ethBalance,
            mTokenRepository.usoBalance,
            mEthereumRepository.ethHeight,
            mTokenRepository.usoSupply
        ) { ethBalance: BigDecimal, usoBalance: BigDecimal, ethHeight: BigInteger, usoSupply: BigDecimal ->
            DashboardInfo(
                ethBalance,
                usoBalance,
                ethHeight,
                usoSupply
            )
        }
            .map {
                mDbRepository.updateDashboardInfo(it)
                it
            }
    }
}