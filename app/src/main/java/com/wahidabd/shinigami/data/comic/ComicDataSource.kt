package com.wahidabd.shinigami.data.comic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.WebApi
import com.wahidabd.shinigami.data.home.model.KomikItem
import io.reactivex.rxjava3.core.Observable


/**
 * Created by Wahid on 4/12/2023.
 * Github github.com/wahidabd.
 */


class ComicDataSource : ComicRepository {

    override val dbService: LocalDb? = null
    override val webService: WebApi? = null

    override fun getPaging(order: String): Observable<PagingData<KomikItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                maxSize = 100,
                enablePlaceholders = false,
            ), pagingSourceFactory = { ComicPagingSource(order) }
        ).observable
    }
}