package com.wahidabd.shinigami.domain.comment.model

import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.shinigami.data.comment.model.CommentItem


/**
 * Created by Wahid on 6/2/2023.
 * Github github.com/wahidabd.
 */


data class Comment(
    val id: String? = emptyString(),
    val name: String? = emptyString(),
    val avatar: String? = emptyString(),
    val body: String? = emptyString(),
    val date: String? = emptyString(),
    val slug: String? = emptyString()
)

fun Comment.toResponse(): CommentItem {
    return CommentItem(
        id, name, avatar, body, date, slug?.replace("series/", "")
    )
}

fun CommentItem.toDomain(): Comment {
    return Comment(
        id, name, avatar, body, date, slug
    )
}