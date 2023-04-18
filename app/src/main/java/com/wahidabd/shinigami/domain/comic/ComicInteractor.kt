package com.wahidabd.shinigami.domain.comic

import androidx.paging.PagingData
import androidx.paging.map
import com.wahidabd.shinigami.data.comic.ComicRepository
import com.wahidabd.shinigami.domain.home.mapper.toDomain
import com.wahidabd.shinigami.domain.home.model.Komik
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable


/**
 * Created by Wahid on 4/12/2023.
 * Github github.com/wahidabd.
 */


class ComicInteractor(private val data: ComicRepository) : ComicUseCase {

    override fun getPaging(order: String): Observable<PagingData<Komik>> {
        return data.getPaging(order).map {
            it.map { komik ->
                komik.toDomain()
            }
        }
    }

}