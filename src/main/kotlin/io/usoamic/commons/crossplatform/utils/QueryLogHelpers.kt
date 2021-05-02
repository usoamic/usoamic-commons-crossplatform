package io.usoamic.commons.crossplatform.utils

import io.usoamic.commons.crossplatform.UsoamicCommonsConfig

internal fun logQuery(tag: String, destination: QueryDestination, msg: String) {
    if(UsoamicCommonsConfig.DEBUG) {
        println("$tag: ${destination.name}::$msg")
    }
}

internal enum class QueryDestination {
    CACHE,
    NETWORK
}