package io.usoamic.commons.crossplatform.exceptions

class NoteNotFoundThrowable(id: Long, forAuthor: Boolean) : Throwable(
    "Note #$id (forAuthor: $forAuthor) not found"
)
