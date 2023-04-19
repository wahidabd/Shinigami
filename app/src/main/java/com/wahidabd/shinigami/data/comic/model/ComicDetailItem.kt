package com.wahidabd.shinigami.data.comic.model

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 4/19/2023.
 * Github github.com/wahidabd.
 */


data class ComicDetailItem(
    val slug: String? = emptyString(),
    val title: String? = emptyString(),
    val imageCover: String? = emptyString(),
    val imageBanner: String? = emptyString(),
    val alternative: String? = emptyString(),
    val status: String? = emptyString(),
    val release: String? = emptyString(),
    val type: String? = emptyString(),
    val synopsis: String? = emptyString(),
    val chapters: List<ChapterItem>? = arrayListOf()
)