package io.usoamic.commons.crossplatform.exceptions

class NoteNotFoundThrowable(id: Long, isAuthor: Boolean) : Throwable(
    "$id (isAuthor: $isAuthor) not found for "
)
