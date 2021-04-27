package io.usoamic.commons.crossplatform.models.ethereum

import io.usoamic.commons.crossplatform.extensions.privateKey
import org.web3j.crypto.Credentials

data class AccountCredentialsInfo(
    val address: String,
    val privateKey: String
)

fun Credentials.toDomain() = AccountCredentialsInfo(
    address,
    privateKey
)