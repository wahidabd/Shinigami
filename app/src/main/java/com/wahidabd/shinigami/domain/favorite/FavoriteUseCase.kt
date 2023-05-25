package com.wahidabd.shinigami.domain.favorite

import com.wahidabd.shinigami.domain.favorite.model.Favorite
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 5/25/2023.
 * Github github.com/wahidabd.
 */


interface FavoriteUseCase {
    fun addFavorite(favoriteEntity: Favorite): Completable
    fun getFavorites(): Single<List<Favorite>>
    fun get(slug: String): Single<Favorite>
    fun remove(favoriteEntity: Favorite): Completable
}