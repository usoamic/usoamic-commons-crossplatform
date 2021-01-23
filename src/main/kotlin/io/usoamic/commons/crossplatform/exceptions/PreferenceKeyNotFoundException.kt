package io.usoamic.commons.crossplatform.exceptions

import java.lang.Exception

class PreferenceKeyNotFoundException(key: String) : Exception("$key not found")