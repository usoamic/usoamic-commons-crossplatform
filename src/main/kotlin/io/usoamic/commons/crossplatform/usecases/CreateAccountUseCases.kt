package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.ethereum.AccountCredentialsInfo
import io.usoamic.commons.crossplatform.repositories.api.EthereumRepository
import javax.inject.Inject

class CreateAccountUseCases @Inject constructor(
    private val mEthereumRepository: EthereumRepository
) {
    fun createCredentials(): Single<AccountCredentialsInfo> = mEthereumRepository.createCredentials()
}