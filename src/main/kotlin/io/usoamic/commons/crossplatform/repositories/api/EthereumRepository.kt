package io.usoamic.commons.crossplatform.repositories.api

import io.reactivex.Single
import io.usoamic.wallet.commons.models.add.AddAccountModel
import io.usoamic.wallet.commons.models.ethereum.AccountCredentials
import io.usoamic.wallet.commons.models.withdraw.WithdrawData
import java.math.BigDecimal
import java.math.BigInteger

interface EthereumRepository {
    fun createCredentials(): Single<AccountCredentials>
    fun addAccount(privateKey: String, password: String): Single<AddAccountModel>
    fun getAddress(password: String): Single<String>
    fun withdraw(
        data: WithdrawData
    ): Single<String>
    val ethBalance: Single<BigDecimal>
    val ethHeight: Single<BigInteger>
}