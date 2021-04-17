package io.usoamic.commons.crossplatform.usecases

import io.usoamic.commons.crossplatform.api.DateCompat
import io.usoamic.commons.crossplatform.repositories.api.PreferencesRepository
import javax.inject.Inject

class PreferencesUseCases @Inject constructor(
    private val mPreferencesRepository: PreferencesRepository,
    private val mDateCompat: DateCompat
) {
    fun updateLastAction() {
        mPreferencesRepository.setLastActionTime(mDateCompat.currentTimestamp)
    }
}