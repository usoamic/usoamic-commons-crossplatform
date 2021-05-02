package io.usoamic.commons.crossplatform.models.common.base

sealed class ErrorArguments(open val throwable: Throwable) {
    val message get() = throwable.message

    data class Warning(override val throwable: Throwable) : ErrorArguments(throwable)
    data class Fatal(override val throwable: Throwable) : ErrorArguments(throwable)
}