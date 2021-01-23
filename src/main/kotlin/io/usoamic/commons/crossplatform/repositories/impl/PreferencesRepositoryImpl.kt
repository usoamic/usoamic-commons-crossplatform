package io.usoamic.commons.crossplatform.repositories.impl

import io.usoamic.commons.crossplatform.api.PreferencesCompat
import io.usoamic.wallet.commons.repositories.api.PreferencesRepository
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val preferences: io.usoamic.commons.crossplatform.api.PreferencesCompat
) : PreferencesRepository {
    override fun getAddress(): String = preferences.getString(io.usoamic.commons.crossplatform.api.PreferencesCompat.Key.ADDRESS)

    override fun setAddress(address: String) = preferences.putString(io.usoamic.commons.crossplatform.api.PreferencesCompat.Key.ADDRESS, address)

    override fun setUnlockTime(timestamp: Long) {
        preferences.putLong(io.usoamic.commons.crossplatform.api.PreferencesCompat.Key.TIMESTAMP, timestamp)
    }

    override fun getUnlockTime(): Long = preferences.getLong(io.usoamic.commons.crossplatform.api.PreferencesCompat.Key.TIMESTAMP)

    override fun removeAll() {
        preferences.removeAll()
    }

    override fun remove(key: String) {
        preferences.remove(key)
    }
}