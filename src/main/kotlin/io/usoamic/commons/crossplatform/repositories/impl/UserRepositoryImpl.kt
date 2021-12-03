package io.usoamic.commons.crossplatform.repositories.impl

import io.reactivex.Single
import io.usoamic.commons.crossplatform.extensions.addDebugDelay
import io.usoamic.commons.crossplatform.repositories.api.UserRepository
import io.usoamic.usoamickt.account.impl.corex.UsoamicAccountImpl
import io.usoamic.usoamickt.corex.Usoamic
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usoamic: UsoamicAccountImpl
    ) : UserRepository {
    override fun hasAccount(): Single<Boolean> {
        return Single.fromCallable {
            usoamic.hasAccount
        }
            .addDebugDelay()
    }

    override fun getAddress(): Single<String> {
        return Single.fromCallable {
            usoamic.address
        }
            .addDebugDelay()
    }

    override fun removeAccount(): Single<Boolean> {
        return Single.fromCallable {
            usoamic.removeWallet()
        }
            .addDebugDelay()
    }
}