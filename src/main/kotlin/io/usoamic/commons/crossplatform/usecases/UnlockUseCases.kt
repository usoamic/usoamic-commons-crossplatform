package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.api.DateCompat
import io.usoamic.commons.crossplatform.repositories.api.*
import javax.inject.Inject

class UnlockUseCases @Inject constructor(
    override val mUserRepository: UserRepository,
    override val mDatabaseRepository: DbRepository,
    override val mPreferencesRepository: PreferencesRepository,
    private val mValidateRepository: ValidateRepository,
    private val mEthereumRepository: EthereumRepository,
    private val mDateCompat: DateCompat
) : UserUseCases(mUserRepository, mDatabaseRepository, mPreferencesRepository) {
    fun getAddress(password: String): Single<String> {
        return mValidateRepository.validatePassword(password)
            .andThen(
                mEthereumRepository.getAddress(password)
            )
    }

    fun saveData(address: String) {
        mPreferencesRepository.setAddress(address)
        mPreferencesRepository.setLastActionTime(mDateCompat.currentTimestamp)
    }
}