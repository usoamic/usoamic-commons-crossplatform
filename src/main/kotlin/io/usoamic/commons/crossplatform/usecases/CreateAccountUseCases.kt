package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.mappers.local.toItem
import io.usoamic.commons.crossplatform.models.usecases.ethereum.AccountCredentialsModel
import io.usoamic.commons.crossplatform.repositories.api.EthereumRepository
import javax.inject.Inject

class CreateAccountUseCases @Inject constructor(
    private val mEthereumRepository: EthereumRepository
) {
    fun createCredentials(): Single<AccountCredentialsModel> {
        return mEthereumRepository.createCredentials().map {
            it.toItem()
        }
    }
}