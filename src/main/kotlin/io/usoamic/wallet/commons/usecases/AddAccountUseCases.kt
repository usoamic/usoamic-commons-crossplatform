package io.usoamic.wallet.commons.usecases

import io.reactivex.Single
import io.usoamic.wallet.commons.models.add.AddAccountModel
import io.usoamic.wallet.commons.repositories.EthereumRepository
import io.usoamic.wallet.commons.repositories.ValidateRepository
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