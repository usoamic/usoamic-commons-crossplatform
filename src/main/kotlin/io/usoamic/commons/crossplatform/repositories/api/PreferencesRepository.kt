package io.usoamic.commons.crossplatform.repositories.api

interface PreferencesRepository {
    fun getAddress(): String
    fun setAddress(address: String)
    fun getLastActionTime(): Long
    fun setLastActionTime(timestamp: Long)
    fun remove(key: String)
    fun removeAll()
}