package io.usoamic.wallet.commons.exceptions

import java.lang.Exception

class PreferenceKeyNotFoundException(key: String) : Exception("$key not found")