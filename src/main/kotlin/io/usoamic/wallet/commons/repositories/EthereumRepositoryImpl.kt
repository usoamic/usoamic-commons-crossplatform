package io.usoamic.wallet.commons.repositories

import io.reactivex.Single
import io.usoamic.usoamickt.core.Usoamic
import io.usoamic.wallet.commons.models.add.AddAccountModel
import io.usoamic.wallet.commons.models.ethereum.AccountCredentials
import io.usoamic.wallet.commons.models.ethereum.toDomain
import io.usoamic.wallet.commons.models.withdraw.WithdrawData
import io.usoamic.wallet.commons.extensions.addDebugDelay
import io.usoamic.wallet.commons.extensions.privateKey
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