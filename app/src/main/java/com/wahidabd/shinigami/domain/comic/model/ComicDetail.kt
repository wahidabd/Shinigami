package com.wahidabd.shinigami.domain.comic.model

import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.shinigami.data.comic.model.ChapterItem


/**
 * Created by Wahid on 4/19/2023.
 * Github github.com/wahidabd.
 */

data class ComicDetail(
    val slug: String? = emptyString(),
    val title: String? = emptyString(),
    val imagePoster: String? = emptyString(),
    val imageBanner: String? = emptyString(),
    val alternative: String? = emptyString(),
    val source: String? = emptyString(),
    val status: String? = emptyString(),
    val release: String? = emptyString(),
    val type: String? = emptyString(),
    val author: String? = emptyString(),
    val synopsis: String? = emptyString(),
    val chapters: List<Chapter>? = arrayListOf()
)