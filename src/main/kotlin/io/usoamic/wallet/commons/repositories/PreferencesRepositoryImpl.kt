package io.usoamic.wallet.commons.repositories

import io.usoamic.wallet.commons.api.PreferencesCompat
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val preferences: PreferencesCompat
) : PreferencesRepository {
    override fun getAddress(): String = preferences.getString(PreferencesCompat.Key.ADDRESS)

    override fun setAddress(address: String) = preferences.putString(PreferencesCompat.Key.ADDRESS, address)

    override fun setUnlockTime(timestamp: Long) {
        preferences.putLong(PreferencesCompat.Key.TIMESTAMP, timestamp)
    }

    override fun getUnlockTime(): Long = preferences.getLong(PreferencesCompat.Key.TIMESTAMP)

    override fun removeAll() {
        preferences.removeAll()
    }

    override fun remove(key: String) {
        preferences.remove(key)
    }
}