package com.wahidabd.shinigami.domain.favorite.model

import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.shinigami.data.favorite.note.FavoriteEntity


/**
 * Created by Wahid on 5/25/2023.
 * Github github.com/wahidabd.
 */


data class Favorite(
    val slug: String? = emptyString(),
    val title: String? = emptyString(),
    val poster: String? = emptyString()
)

fun FavoriteEntity.toDomain(): Favorite {
    return Favorite(
        slug, title, poster
    )
}

fun Favorite.toEntity(): FavoriteEntity {
    return FavoriteEntity(
        slug.toString(), title, poster
    )
}