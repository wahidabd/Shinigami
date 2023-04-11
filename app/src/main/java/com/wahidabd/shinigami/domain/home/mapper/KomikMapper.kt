package com.wahidabd.shinigami.domain.home.mapper

import com.wahidabd.shinigami.data.home.model.KomikItem
import com.wahidabd.shinigami.domain.home.model.Komik


/**
 * Created by Wahid on 4/7/2023.
 * Github github.com/wahidabd.
 */


fun KomikItem.toDomain(): Komik =
    Komik(slug, title, cover, type)