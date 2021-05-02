package io.usoamic.commons.crossplatform.mappers.entity

import io.usoamic.commons.crossplatform.extensions.privateKey
import io.usoamic.commons.crossplatform.models.repository.ethereum.AccountCredentialsEntity
import org.web3j.crypto.Credentials

internal fun Credentials.toEntity() = AccountCredentialsEntity(
    address,
    privateKey
)