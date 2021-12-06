package io.usoamic.commons.crossplatform.exceptions

class TransactionNotFoundThrowable(
    id: Long,
    address: String
) : Throwable(
    "Transaction #$id (forAddress: $address) not found"
)