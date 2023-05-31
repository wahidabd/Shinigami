package com.wahidabd.shinigami.domain.history.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.shinigami.data.history.dao.HistoryEntity
import com.wahidabd.shinigami.utils.currentTimestamp


/**
 * Created by Wahid on 5/31/2023.
 * Github github.com/wahidabd.
 */

@RequiresApi(Build.VERSION_CODES.O)
data class History(
    val slug: String? = emptyString(),
    val chapter_slug: String? = emptyString(),
    val title: String? = emptyString(),
    val chapter_title: String? = emptyString(),
    val poster: String? = emptyString(),
    val date: String? = currentTimestamp()
)

@RequiresApi(Build.VERSION_CODES.O)
fun HistoryEntity.toDomain(): History {
    return History(
        slug, chapter_slug, title, chapter_title, poster, date
    )
}

fun History.toEntity(): HistoryEntity {
    return HistoryEntity(
        slug.toString(), chapter_slug, title, chapter_title, poster, date
    )
}