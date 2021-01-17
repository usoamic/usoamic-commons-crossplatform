package io.usoamic.wallet.commons.models.base

sealed class ErrorArguments(val throwable: Throwable) {
    val message get() = throwable.message

    data class Warning(private val t: Throwable) : ErrorArguments(t)
    data class Fatal(private val t: Throwable) : ErrorArguments(t)
}