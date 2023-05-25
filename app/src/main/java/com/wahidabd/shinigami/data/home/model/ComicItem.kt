package com.wahidabd.shinigami.data.home.model

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */


data class ComicItem(
    val slug: String,
    val title: String,
    val cover: String,
    val type: String? = emptyString(),
    val rating: String? = emptyString()
)
