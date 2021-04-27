package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.repository.withdraw.WithdrawRequest
import io.usoamic.commons.crossplatform.models.usecases.withdraw.WithdrawCoinTicker
import io.usoamic.commons.crossplatform.repositories.api.EthereumRepository
import io.usoamic.commons.crossplatform.repositories.api.TokenRepository
import io.usoamic.commons.crossplatform.repositories.api.ValidateRepository
import io.usoamic.usoamickt.enumcls.TxSpeed
import java.math.BigDecimal
import javax.inject.Inject

class WithdrawUseCases @Inject constructor(
    private val mValidateRepository: ValidateRepository,
    private val mTokenRepository: TokenRepository,
    private val mEthereumRepository: EthereumRepository
) {
    fun withdraw(
        coin: WithdrawCoinTicker,
        password: String,
        to: String,
        value: String,
        txSpeed: String
    ): Single<String> {
        return mValidateRepository.validatePassword(password)
            .andThen(mValidateRepository.validateAddress(to))
            .andThen(mValidateRepository.validateTransferValue(value))
            .andThen(
                Single.defer {
                    val data = WithdrawRequest(
                        password = password,
                        to = to,
                        value = BigDecimal(value),
                        txSpeed = TxSpeed.parseString(txSpeed)
                    )
                    when (coin) {
                        WithdrawCoinTicker.ETH -> {
                            mEthereumRepository.withdraw(data)
                        }
                        WithdrawCoinTicker.USO -> {
                            mTokenRepository.withdraw(data)
                        }
                    }
                }
            )
    }
}