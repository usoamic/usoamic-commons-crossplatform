package io.usoamic.commons.crossplatform.mappers.local

import io.usoamic.commons.crossplatform.models.repository.add.AddAccountEntity
import io.usoamic.commons.crossplatform.models.usecases.add.AddAccountModel

fun AddAccountEntity.toItem(): AddAccountModel {
    return AddAccountModel(
        name = name
    )
}