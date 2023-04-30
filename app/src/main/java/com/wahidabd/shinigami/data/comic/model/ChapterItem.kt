package com.wahidabd.shinigami.data.comic.model

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 4/19/2023.
 * Github github.com/wahidabd.
 */


data class ChapterItem(
    val slug: String? = emptyString(),
    val imageCover: String? = emptyString(),
    val title: String? = emptyString(),
    val time: String? = emptyString()
)
