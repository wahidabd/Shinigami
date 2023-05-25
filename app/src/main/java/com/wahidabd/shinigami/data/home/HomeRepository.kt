package com.wahidabd.shinigami.data.home

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.shinigami.data.home.model.ComicItem
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 4/7/2023.
 * Github github.com/wahidabd.
 */


interface HomeRepository : BaseRepository {

    fun getPopular(): Single<List<ComicItem>>
    fun getLatest(): Single<List<ComicItem>>
    fun getTrending(): Single<List<ComicItem>>
    fun getMirror(): Single<List<ComicItem>>

}