package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.add.AddAccountModel
import io.usoamic.commons.crossplatform.repositories.api.EthereumRepository
import io.usoamic.commons.crossplatform.repositories.api.ValidateRepository
import javax.inject.Inject

class AddAccountUseCases @Inject constructor(
    private val mValidateRepository: ValidateRepository,
    private val mEthereumRepository: EthereumRepository
) {
    fun addAccount(
        privateKey: String,
        password: String,
        confirmPassword: String
    ): Single<AddAccountModel> {
        return mValidateRepository.validatePrivateKey(privateKey)
            .andThen(
                mValidateRepository.validatePasswords(password, confirmPassword)
            )
            .andThen(
                mEthereumRepository.addAccount(privateKey, password)
            )
    }
}