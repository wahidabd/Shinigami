package com.wahidabd.shinigami.domain.comic

import androidx.paging.PagingData
import com.wahidabd.library.data.BaseRepository
import com.wahidabd.shinigami.data.home.model.KomikItem
import com.wahidabd.shinigami.domain.home.model.Komik
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 4/12/2023.
 * Github github.com/wahidabd.
 */


interface ComicUseCase {

    fun getPaging(order: String): Observable<PagingData<Komik>>

}