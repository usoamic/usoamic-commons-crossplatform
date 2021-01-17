package io.usoamic.wallet.commons.repositories.api

interface PreferencesRepository {
    fun getAddress(): String
    fun setAddress(address: String)
    fun getUnlockTime(): Long
    fun setUnlockTime(timestamp: Long)
    fun remove(key: String)
    fun removeAll()
}