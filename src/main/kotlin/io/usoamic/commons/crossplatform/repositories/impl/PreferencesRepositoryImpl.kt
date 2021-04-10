package io.usoamic.commons.crossplatform.repositories.impl

import io.usoamic.commons.crossplatform.api.PreferencesCompat
import io.usoamic.commons.crossplatform.repositories.api.PreferencesRepository
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val preferences: PreferencesCompat
) : PreferencesRepository {
    override fun getAddress(): String = preferences.getString(
        key = PreferencesCompat.Key.ADDRESS
    )

    override fun setAddress(address: String) = preferences.putString(
        key = PreferencesCompat.Key.ADDRESS,
        value = address
    )

    override fun setLastActionTime(timestamp: Long) = preferences.putLong(
        key = PreferencesCompat.Key.LAST_ACTION_TIMESTAMP,
        value = timestamp
    )

    override fun getLastActionTime(): Long = preferences.getLong(
        key = PreferencesCompat.Key.LAST_ACTION_TIMESTAMP
    )

    override fun removeAll() {
        preferences.removeAll()
    }

    override fun remove(key: String) {
        preferences.remove(key)
    }
}