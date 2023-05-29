package com.wahidabd.shinigami.domain.comic.mapper

import com.wahidabd.shinigami.data.comic.model.ContentReaderItem
import com.wahidabd.shinigami.data.comic.model.ReaderItem
import com.wahidabd.shinigami.domain.comic.model.ContentReader
import com.wahidabd.shinigami.domain.comic.model.Reader


/**
 * Created by Wahid on 5/23/2023.
 * Github github.com/wahidabd.
 */


fun ReaderItem.toDomain(): Reader {
    return Reader(
        prev = prev,
        next = next,
        title = title,
        items = items.map {
            it.toDomain()
        }
    )
}

fun ContentReaderItem.toDomain(): ContentReader {
    return ContentReader(
        id = id,
        image = image
    )
}