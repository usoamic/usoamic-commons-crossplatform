package io.usoamic.wallet.commons.repositories

import io.reactivex.Single
import io.usoamic.usoamickt.core.Usoamic
import io.usoamic.wallet.commons.extensions.addDebugDelay
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val usoamic: Usoamic) : UserRepository {
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