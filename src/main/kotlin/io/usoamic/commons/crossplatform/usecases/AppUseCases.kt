package io.usoamic.commons.crossplatform.usecases

import io.usoamic.commons.crossplatform.api.DateCompat
import io.usoamic.commons.crossplatform.repositories.api.PreferencesRepository
import javax.inject.Inject

class AppUseCases @Inject constructor(
    private val mPreferencesRepository: PreferencesRepository,
    private val mDateCompat: DateCompat
) {
    fun getLastAction(): Long {
        return mPreferencesRepository.getLastActionTime()
    }

    fun updateLastAction() {
        mPreferencesRepository.setLastActionTime(mDateCompat.currentTimestamp)
    }

    companion object {
        const val TAG: String = "PreferencesUseCases"
    }
}