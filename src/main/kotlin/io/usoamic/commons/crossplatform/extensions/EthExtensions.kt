package io.usoamic.commons.crossplatform.extensions

import org.web3j.crypto.Credentials
import org.web3j.utils.Numeric

val Credentials.privateKey: String get() = ecKeyPair.privateKey.toString(16)

fun String.toCleanedHexPrefixString(): String {
    return Numeric.cleanHexPrefix(this)
}