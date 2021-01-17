package io.usoamic.wallet.commons.models.ethereum

import io.usoamic.wallet.commons.extensions.privateKey
import org.web3j.crypto.Credentials

data class AccountCredentials(
    val address: String,
    val privateKey: String
)

fun Credentials.toDomain() = AccountCredentials(
    address,
    privateKey
)