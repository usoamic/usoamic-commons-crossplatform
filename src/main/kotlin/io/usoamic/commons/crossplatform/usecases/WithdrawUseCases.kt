package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.withdraw.WithdrawCoin
import io.usoamic.commons.crossplatform.models.withdraw.WithdrawData
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
        coin: WithdrawCoin,
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
                    val data = WithdrawData(
                        password = password,
                        to = to,
                        value = BigDecimal(value),
                        txSpeed = TxSpeed.parseString(txSpeed)
                    )
                    when (coin) {
                        WithdrawCoin.ETH -> {
                            mEthereumRepository.withdraw(data)
                        }
                        WithdrawCoin.USO -> {
                            mTokenRepository.withdraw(data)
                        }
                    }
                }
            )
    }
}