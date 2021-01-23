package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.wallet.commons.repositories.api.UserRepository
import javax.inject.Inject

class StartUseCases @Inject constructor(
    private val mUserRepository: UserRepository
) {
    fun hasAccount(): Single<Boolean> {
        return mUserRepository.hasAccount()
    }
}