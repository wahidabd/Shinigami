package com.wahidabd.shinigami.data.comment.model

import com.google.firebase.database.Exclude
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 6/2/2023.
 * Github github.com/wahidabd.
 */


data class CommentItem(
    val id: String? = emptyString(),
    val name: String? = emptyString(),
    val avatar: String? = emptyString(),
    val body: String? = emptyString(),
    val date: String? = emptyString(),
    val slug: String? = emptyString()
){
    @Exclude
    fun toMap(): Map<String, Any?>{
        return mapOf(
            "id" to id,
            "name" to name,
            "avatar" to avatar,
            "body" to body,
            "date" to date,
            "slug" to slug
        )
    }
}