package io.usoamic.wallet.commons.repositories

import io.reactivex.Single

interface UserRepository {
    fun hasAccount(): Single<Boolean>
    fun getAddress(): Single<String>
    fun removeAccount(): Single<Boolean>
}