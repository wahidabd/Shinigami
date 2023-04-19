package com.wahidabd.shinigami.domain.comic.model

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 4/19/2023.
 * Github github.com/wahidabd.
 */

data class Chapter(
    val slug: String? = emptyString(),
    val imageCover: String? = emptyString(),
    val title: String? = emptyString(),
    val releaseDate: String? = emptyString()
)