package io.usoamic.commons.crossplatform.repositories.impl

import io.reactivex.Single
import io.usoamic.commons.crossplatform.extensions.addDebugDelay
import io.usoamic.commons.crossplatform.extensions.privateKey
import io.usoamic.commons.crossplatform.models.add.AddAccountModel
import io.usoamic.commons.crossplatform.models.ethereum.AccountCredentials
import io.usoamic.commons.crossplatform.models.ethereum.toDomain
import io.usoamic.commons.crossplatform.models.withdraw.WithdrawData
import io.usoamic.commons.crossplatform.repositories.api.EthereumRepository
import io.usoamic.usoamickt.core.Usoamic
import org.web3j.crypto.Credentials
import org.web3j.crypto.Keys
import org.web3j.crypto.WalletUtils
import org.web3j.utils.Convert
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

class EthereumRepositoryImpl @Inject constructor(
    private val usoamic: Usoamic
) : EthereumRepository {
    override fun addAccount(privateKey: String, password: String): Single<AddAccountModel> {
        return Single.fromCallable {
            AddAccountModel(
                usoamic.importPrivateKey(password, privateKey)
            )
        }
            .addDebugDelay()
    }

    override fun getAddress(password: String): Single<String> {
        return Single.fromCallable {
            usoamic.getAddress(password)
        }
            .addDebugDelay()
    }

    override fun createCredentials(): Single<AccountCredentials> {
        return Single.fromCallable {
            var credentials: Credentials
            do {
                credentials = Credentials.create(Keys.createEcKeyPair())
            } while (!WalletUtils.isValidPrivateKey(credentials.privateKey))
            credentials.toDomain()
        }
            .addDebugDelay()
    }

    override val ethBalance: Single<BigDecimal>
        get() {
            return Single.fromCallable {
                usoamic.getConvertedBalance()
            }
                .addDebugDelay()
        }

    override val ethHeight: Single<BigInteger>
        get() {
            return Single.fromCallable {
                usoamic.getEthHeight()
            }
                .addDebugDelay()
        }

    override fun withdraw(data: WithdrawData): Single<String> {
        return Single.fromCallable {
            val value = Convert.toWei(data.value, Convert.Unit.ETHER)
            usoamic.transferEth(
                password = data.password,
                to = data.to,
                value = value.toBigInteger(),
                txSpeed = data.txSpeed
            )
        }.addDebugDelay()
    }
}