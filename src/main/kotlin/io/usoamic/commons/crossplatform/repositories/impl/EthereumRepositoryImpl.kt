package io.usoamic.commons.crossplatform.repositories.impl

import io.reactivex.Single
import io.usoamic.commons.crossplatform.extensions.addDebugDelay
import io.usoamic.commons.crossplatform.extensions.privateKey
import io.usoamic.commons.crossplatform.mappers.entity.toEntity
import io.usoamic.commons.crossplatform.models.repository.add.AddAccountEntity
import io.usoamic.commons.crossplatform.models.repository.ethereum.AccountCredentialsEntity
import io.usoamic.commons.crossplatform.models.repository.withdraw.WithdrawRequest
import io.usoamic.commons.crossplatform.repositories.api.EthereumRepository
import io.usoamic.usoamickt.account.impl.corex.UsoamicAccountImpl
import org.web3j.crypto.Credentials
import org.web3j.crypto.Keys
import org.web3j.crypto.WalletUtils
import org.web3j.utils.Convert
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

class EthereumRepositoryImpl @Inject constructor(
    private val usoamicAccount: UsoamicAccountImpl
) : EthereumRepository {
    override fun addAccount(privateKey: String, password: String): Single<AddAccountEntity> {
        return Single.fromCallable {
            AddAccountEntity(
                usoamicAccount.importPrivateKey(password, privateKey)
            )
        }
            .addDebugDelay()
    }

    override fun getAddress(password: String): Single<String> {
        return Single.fromCallable {
            usoamicAccount.getAddress(password)
        }
            .addDebugDelay()
    }

    override fun createCredentials(): Single<AccountCredentialsEntity> {
        return Single.fromCallable {
            var credentials: Credentials
            do {
                credentials = Credentials.create(Keys.createEcKeyPair())
            } while (!WalletUtils.isValidPrivateKey(credentials.privateKey))
            credentials.toEntity()
        }
            .addDebugDelay()
    }

    override val ethBalance: Single<BigDecimal>
        get() {
            return Single.fromCallable {
                usoamicAccount.getConvertedBalance()
            }
                .addDebugDelay()
        }

    override val ethHeight: Single<BigInteger>
        get() {
            return Single.fromCallable {
                usoamicAccount.getEthHeight()
            }
                .addDebugDelay()
        }

    override fun withdraw(data: WithdrawRequest): Single<String> {
        return Single.fromCallable {
            val value = Convert.toWei(data.value, Convert.Unit.ETHER)
            usoamicAccount.transferEth(
                password = data.password,
                to = data.to,
                value = value.toBigInteger(),
                txSpeed = data.txSpeed
            )
        }.addDebugDelay()
    }
}