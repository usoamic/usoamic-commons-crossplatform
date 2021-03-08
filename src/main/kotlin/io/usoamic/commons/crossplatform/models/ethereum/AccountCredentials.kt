package io.usoamic.commons.crossplatform.models.ethereum

import io.usoamic.commons.crossplatform.extensions.privateKey
import org.web3j.crypto.Credentials

data class AccountCredentials(
    val address: String,
    val privateKey: String
)

fun Credentials.toDomain() = AccountCredentials(
    address,
    privateKey
)