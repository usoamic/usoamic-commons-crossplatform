package io.usoamic.commons.crossplatform.api

interface PreferencesCompat {
    fun getString(key: String): String
    fun getLong(key: String): Long
    fun putString(key: String, value: String)
    fun putLong(key: String, value: Long)
    fun remove(key: String)
    fun removeAll()

    object Key {
        const val SHARED_PREFS: String = "sharedPrefs"
        const val LAST_ACTION_TIMESTAMP: String = "date"
        const val ADDRESS: String = "address"
    }
}

