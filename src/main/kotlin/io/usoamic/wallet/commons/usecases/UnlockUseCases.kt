package io.usoamic.wallet.commons.usecases

import io.reactivex.Single
import io.usoamic.wallet.commons.repositories.api.EthereumRepository
import io.usoamic.wallet.commons.repositories.api.PreferencesRepository
import io.usoamic.wallet.commons.repositories.api.UserRepository
import io.usoamic.wallet.commons.repositories.api.ValidateRepository
import io.usoamic.wallet.commons.api.DateCompat
import javax.inject.Inject

class UnlockUseCases @Inject constructor(
    private val mValidateRepository: ValidateRepository,
    private val mEthereumRepository: EthereumRepository,
    private val mUserRepository: UserRepository,
    private val mPreferencesRepository: PreferencesRepository,
    private val mDateCompat: DateCompat
) {
    fun getAddress(password: String): Single<String> {
        return mValidateRepository.validatePassword(password)
            .andThen(
                mEthereumRepository.getAddress(password)
                    .map {
                        it
                    }
            )
    }

    fun saveData(address: String) {
        mPreferencesRepository.setAddress(address)
        mPreferencesRepository.setUnlockTime(mDateCompat.currentTimestamp)
    }

    fun removePreferences() = mPreferencesRepository.removeAll()

    fun removeAccount() = mUserRepository.removeAccount()
}