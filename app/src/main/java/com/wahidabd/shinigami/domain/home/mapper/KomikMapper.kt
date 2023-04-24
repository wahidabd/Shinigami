package com.wahidabd.shinigami.domain.home.mapper

import com.wahidabd.shinigami.data.home.model.ComicItem
import com.wahidabd.shinigami.domain.home.model.Comic


/**
 * Created by Wahid on 4/7/2023.
 * Github github.com/wahidabd.
 */


fun ComicItem.toDomain(): Comic =
    Comic(slug, title, cover, type)