package com.wahidabd.shinigami.data.history.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wahidabd.library.base.Model
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 5/31/2023.
 * Github github.com/wahidabd.
 */


@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "slug")
    val slug: String,
    @ColumnInfo(name = "chapter_slug")
    val chapter_slug: String? = emptyString(),
    @ColumnInfo(name = "title")
    val title: String? = emptyString(),
    @ColumnInfo(name = "chapter_title")
    val chapter_title: String? = emptyString(),
    @ColumnInfo(name = "poster")
    val poster: String? = emptyString(),
    @ColumnInfo(name = "date")
    val date: String? = emptyString()
) : Model()