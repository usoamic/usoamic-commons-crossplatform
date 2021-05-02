package io.usoamic.commons.crossplatform.mappers.local

import io.usoamic.commons.crossplatform.models.repository.ethereum.AccountCredentialsEntity
import io.usoamic.commons.crossplatform.models.usecases.ethereum.AccountCredentialsModel

fun AccountCredentialsEntity.toItem(): AccountCredentialsModel {
    return AccountCredentialsModel(
        address = address,
        privateKey = privateKey
    )
}