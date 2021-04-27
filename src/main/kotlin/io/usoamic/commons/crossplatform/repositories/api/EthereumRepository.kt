package io.usoamic.commons.crossplatform.repositories.api

import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.repository.add.AddAccountEntity
import io.usoamic.commons.crossplatform.models.repository.ethereum.AccountCredentialsEntity
import io.usoamic.commons.crossplatform.models.repository.withdraw.WithdrawRequest
import java.math.BigDecimal
import java.math.BigInteger

interface EthereumRepository {
    fun createCredentials(): Single<AccountCredentialsEntity>
    fun addAccount(privateKey: String, password: String): Single<AddAccountEntity>
    fun getAddress(password: String): Single<String>
    fun withdraw(
        data: WithdrawRequest
    ): Single<String>
    val ethBalance: Single<BigDecimal>
    val ethHeight: Single<BigInteger>
}