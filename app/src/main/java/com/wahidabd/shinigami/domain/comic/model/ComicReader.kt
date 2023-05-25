package com.wahidabd.shinigami.domain.comic.model

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 5/23/2023.
 * Github github.com/wahidabd.
 */


data class Reader(
    val prev: String? = emptyString(),
    val next: String? = emptyString(),
    val items: List<ContentReader>
)

data class ContentReader(
    val id: String,
    val image: String
)