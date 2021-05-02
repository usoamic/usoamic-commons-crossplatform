package io.usoamic.commons.crossplatform.models.usecases.notes

sealed class NoteItem(
    open val id: Long,
    open val content: String,
    open val author: String,
    open val timestamp: Long,
    open val isAuthor: Boolean
) {
    data class Public(
        override val id: Long,
        override val content: String,
        override val author: String,
        override val timestamp: Long,
        override val isAuthor: Boolean
    ) : NoteItem(id, content, author, timestamp, isAuthor)

    data class Unlisted(
        override val id: Long,
        override val content: String,
        override val author: String,
        override val timestamp: Long,
        override val isAuthor: Boolean
    ) : NoteItem(id, content, author, timestamp, isAuthor)
}

