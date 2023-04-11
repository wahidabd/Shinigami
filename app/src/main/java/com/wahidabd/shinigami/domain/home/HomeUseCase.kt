package com.wahidabd.shinigami.domain.home

import com.wahidabd.shinigami.domain.home.model.Komik
import com.wahidabd.shinigami.utils.Quadruple
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 4/7/2023.
 * Github github.com/wahidabd.
 */


interface HomeUseCase {
    fun popularLatest(): Single<Pair<List<Komik>, List<Komik>>>
    fun trendingMirror(): Single<Pair<List<Komik>, List<Komik>>>
    fun getHomeData(): Single<Quadruple<List<Komik>, List<Komik>, List<Komik>, List<Komik>>>
}