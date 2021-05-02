package io.usoamic.commons.crossplatform.repositories.api

import io.reactivex.Completable

interface ValidateRepository {
    fun validatePasswords(password: String, confirmPassword: String): Completable
    fun validatePassword(password: String): Completable
    fun validatePrivateKey(privateKey: String): Completable
    fun validateAddress(address: String): Completable
    fun validateTransferValue(value: String): Completable
    fun validateContent(content: String): Completable
}