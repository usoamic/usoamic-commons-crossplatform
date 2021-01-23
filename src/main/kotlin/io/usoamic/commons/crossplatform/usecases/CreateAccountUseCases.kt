package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.wallet.commons.models.ethereum.AccountCredentials
import io.usoamic.wallet.commons.repositories.api.EthereumRepository
import javax.inject.Inject

class CreateAccountUseCases @Inject constructor(
    private val mEthereumRepository: EthereumRepository
) {
    fun createCredentials(): Single<AccountCredentials> = mEthereumRepository.createCredentials()
}