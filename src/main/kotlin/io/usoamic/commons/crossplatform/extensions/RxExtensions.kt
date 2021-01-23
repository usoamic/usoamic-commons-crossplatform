package io.usoamic.commons.crossplatform.extensions

import io.reactivex.Completable
import io.reactivex.Single

import io.reactivex.schedulers.Schedulers
import io.usoamic.commons.crossplatform.Config
import java.util.concurrent.TimeUnit

fun <T> Single<T>.subscribeOnIo(): Single<T> = subscribeOn(Schedulers.io())

fun <T> Single<T>.addDebugDelay(): Single<T> {
    if(io.usoamic.commons.crossplatform.Config.DEBUG) {
        return delay(2, TimeUnit.SECONDS)
    }
    return this
}

fun Completable.addDebugDelay(): Completable {
    if(io.usoamic.commons.crossplatform.Config.DEBUG) {
        return delay(2, TimeUnit.SECONDS)
    }
    return this
}
