package io.usoamic.commons.crossplatform.repositories.impl

import io.reactivex.Single
import io.usoamic.commons.crossplatform.extensions.addDebugDelay
import io.usoamic.commons.crossplatform.repositories.api.UserRepository
import io.usoamic.usoamickt.account.api.UsoamicAccount
import io.usoamic.usoamickt.account.impl.corex.UsoamicAccountImpl
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usoamicAccount: UsoamicAccount
    ) : UserRepository {
    override fun hasAccount(): Single<Boolean> {
        return Single.fromCallable {
            usoamicAccount.hasAccount
        }
            .addDebugDelay()
    }

    override fun getAddress(): Single<String> {
        return Single.fromCallable {
            usoamicAccount.address
        }
            .addDebugDelay()
    }

    override fun removeAccount(): Single<Boolean> {
        return Single.fromCallable {
            usoamicAccount.removeWallet()
        }
            .addDebugDelay()
    }
}