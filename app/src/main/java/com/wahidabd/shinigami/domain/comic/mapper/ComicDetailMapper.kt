package com.wahidabd.shinigami.domain.comic.mapper

import com.wahidabd.shinigami.data.comic.model.ChapterItem
import com.wahidabd.shinigami.data.comic.model.ComicDetailItem
import com.wahidabd.shinigami.domain.comic.model.Chapter
import com.wahidabd.shinigami.domain.comic.model.ComicDetail


/**
 * Created by Wahid on 4/23/2023.
 * Github github.com/wahidabd.
 */


fun ComicDetailItem.toDomain(): ComicDetail =
    ComicDetail(
        slug, title, imagePoster, imageBanner, alternative, source, status, release, type, author, synopsis,
        chapters?.map {
            it.toDomain()
        }
    )

fun ChapterItem.toDomain(): Chapter =
    Chapter(slug, imageCover, title, releaseDate)