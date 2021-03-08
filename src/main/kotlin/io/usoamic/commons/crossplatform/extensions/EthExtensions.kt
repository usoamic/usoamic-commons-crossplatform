package io.usoamic.commons.crossplatform.extensions

import org.web3j.crypto.Credentials

val Credentials.privateKey: String get() = ecKeyPair.privateKey.toString(16)