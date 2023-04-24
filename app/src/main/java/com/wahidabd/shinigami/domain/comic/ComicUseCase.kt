package com.wahidabd.shinigami.domain.comic

import androidx.paging.PagingData
import com.wahidabd.shinigami.domain.comic.model.ComicDetail
import com.wahidabd.shinigami.domain.home.model.Comic
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 4/12/2023.
 * Github github.com/wahidabd.
 */


interface ComicUseCase {

    fun getPaging(order: String): Observable<PagingData<Comic>>
    fun getDetail(slug: String): Single<ComicDetail>

}