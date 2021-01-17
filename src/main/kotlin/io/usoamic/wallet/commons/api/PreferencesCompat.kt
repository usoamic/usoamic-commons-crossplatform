package io.usoamic.wallet.commons.api

interface PreferencesCompat {
    fun getString(key: String): String
    fun getLong(key: String): Long
    fun putString(key: String, value: String)
    fun putLong(key: String, value: Long)
    fun remove(key: String)
    fun removeAll()

    object Key {
        const val SHARED_PREFS: String = "sharedPrefs"
        const val TIMESTAMP: String = "date"
        const val ADDRESS: String = "address"
    }
}

