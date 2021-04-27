package io.usoamic.commons.crossplatform.repositories.api

import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.add.AddAccountModel
import io.usoamic.commons.crossplatform.models.ethereum.AccountCredentialsInfo
import io.usoamic.commons.crossplatform.models.withdraw.WithdrawInfo
import java.math.BigDecimal
import java.math.BigInteger

interface EthereumRepository {
    fun createCredentials(): Single<AccountCredentialsInfo>
    fun addAccount(privateKey: String, password: String): Single<AddAccountModel>
    fun getAddress(password: String): Single<String>
    fun withdraw(
        data: WithdrawInfo
    ): Single<String>
    val ethBalance: Single<BigDecimal>
    val ethHeight: Single<BigInteger>
}