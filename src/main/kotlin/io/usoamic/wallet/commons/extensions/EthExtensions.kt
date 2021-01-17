package io.usoamic.wallet.commons.extensions

import org.web3j.crypto.Credentials

val Credentials.privateKey: String get() = ecKeyPair.privateKey.toString(16)