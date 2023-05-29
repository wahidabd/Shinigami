package com.wahidabd.shinigami.data.comic.model

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 5/23/2023.
 * Github github.com/wahidabd.
 */


data class ReaderItem(
    val prev: String? = emptyString(),
    val next: String? = emptyString(),
    val title: String? = emptyString(),
    val items: List<ContentReaderItem>
)

data class ContentReaderItem(
    val id: String,
    val image: String
)