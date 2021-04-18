package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Completable
import io.reactivex.Single
import io.usoamic.commons.crossplatform.api.DateCompat
import io.usoamic.commons.crossplatform.repositories.api.*
import javax.inject.Inject

class UnlockUseCases @Inject constructor(
    private val mValidateRepository: ValidateRepository,
    private val mEthereumRepository: EthereumRepository,
    private val mUserRepository: UserRepository,
    private val mPreferencesRepository: PreferencesRepository,
    private val mDateCompat: DateCompat,
    private val mDatabaseRepository: DbRepository
) {
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

    fun removePreferences() {
        mPreferencesRepository.removeAll()
    }

    fun removeAccount(): Completable {
        return mUserRepository.removeAccount()
            .ignoreElement()
    }

    fun clearDb(): Completable {
        return Completable.fromCallable {
            mDatabaseRepository.removeAll()
        }
    }
}