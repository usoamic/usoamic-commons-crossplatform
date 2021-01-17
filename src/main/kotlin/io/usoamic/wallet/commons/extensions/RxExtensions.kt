package io.usoamic.wallet.commons.extensions

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import io.usoamic.wallet.commons.Config
import java.util.concurrent.TimeUnit

fun <T> Single<T>.observeOnMain(): Single<T> = observeOn(JavaFxScheduler.platform())

fun <T> Single<T>.subscribeOnIo(): Single<T> = subscribeOn(Schedulers.io())

fun <T> Single<T>.addSchedulers(): Single<T> = subscribeOnIo().observeOnMain()

fun <T> Single<T>.addDebugDelay(): Single<T> {
    if(Config.DEBUG) {
        return delay(2, TimeUnit.SECONDS)
    }
    return this
}

fun Completable.addDebugDelay(): Completable {
    if(Config.DEBUG) {
        return delay(2, TimeUnit.SECONDS)
    }
    return this
}
