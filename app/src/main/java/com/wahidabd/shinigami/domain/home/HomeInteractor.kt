package com.wahidabd.shinigami.domain.home

import com.wahidabd.shinigami.data.home.HomeRepository
import com.wahidabd.shinigami.domain.home.mapper.toDomain
import com.wahidabd.shinigami.domain.home.model.Komik
import com.wahidabd.shinigami.utils.Quadruple
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 4/7/2023.
 * Github github.com/wahidabd.
 */


class HomeInteractor(private val repository: HomeRepository) : HomeUseCase {

    override fun getHomeData(): Single<Quadruple<List<Komik>, List<Komik>, List<Komik>, List<Komik>>> {
        return Single.zip(
            repository.getPopular(),
            repository.getLatest(),
            repository.getTrending(),
            repository.getMirror()
        )
        { popular, latest, trending, mirror ->
            Quadruple(
                popular.map {
                    it.toDomain()
                },
                latest.map {
                    it.toDomain()
                },
                trending.map {
                    it.toDomain()
                },
                mirror.map {
                    it.toDomain()
                }

            )
        }
    }


}