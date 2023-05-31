package com.wahidabd.shinigami.data.favorite.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wahidabd.library.base.Model
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 5/25/2023.
 * Github github.com/wahidabd.
 */


@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "slug")
    val slug: String,
    @ColumnInfo(name = "title")
    val title: String? = emptyString(),
    @ColumnInfo(name = "poster")
    val poster: String? = emptyString()
): Model()