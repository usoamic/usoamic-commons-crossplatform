package io.usoamic.commons.crossplatform.utils

fun use(callback: () -> Unit) {
    try {
        callback.invoke()
    }
    catch (e: Throwable) {

    }
}