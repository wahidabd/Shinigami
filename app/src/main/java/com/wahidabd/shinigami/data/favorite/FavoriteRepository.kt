package com.wahidabd.shinigami.data.favorite

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.shinigami.data.favorite.dao.FavoriteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 5/25/2023.
 * Github github.com/wahidabd.
 */

interface FavoriteRepository : BaseRepository {
    fun addFavorite(favoriteEntity: FavoriteEntity): Completable
    fun getFavorites(): Single<List<FavoriteEntity>>
    fun get(slug: String): Single<FavoriteEntity>
    fun remove(favoriteEntity: FavoriteEntity): Completable
}