package io.usoamic.commons.crossplatform.repositories.api

interface PreferencesRepository {
    fun getAddress(): String
    fun setAddress(address: String)
    fun getUnlockTime(): Long
    fun setUnlockTime(timestamp: Long)
    fun remove(key: String)
    fun removeAll()
}