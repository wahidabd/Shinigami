package com.wahidabd.shinigami.data.home

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.shinigami.data.home.model.KomikItem
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 4/7/2023.
 * Github github.com/wahidabd.
 */


interface HomeRepository : BaseRepository {

    fun getPopular(): Single<List<KomikItem>>
    fun getLatest(): Single<List<KomikItem>>
    fun getTrending(): Single<List<KomikItem>>
    fun getMirror(): Single<List<KomikItem>>

}