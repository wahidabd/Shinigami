package com.wahidabd.shinigami.domain.home

import com.wahidabd.shinigami.domain.home.model.Comic
import com.wahidabd.shinigami.utils.Quadruple
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 4/7/2023.
 * Github github.com/wahidabd.
 */


interface HomeUseCase {
    fun getHomeData(): Single<Quadruple<List<Comic>, List<Comic>, List<Comic>, List<Comic>>>
}