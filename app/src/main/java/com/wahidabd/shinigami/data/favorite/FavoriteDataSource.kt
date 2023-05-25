package com.wahidabd.shinigami.data.favorite

import com.wahidabd.library.data.LocalDb
import com.wahidabd.shinigami.data.favorite.note.FavoriteDao
import com.wahidabd.shinigami.data.favorite.note.FavoriteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 5/25/2023.
 * Github github.com/wahidabd.
 */


class FavoriteDataSource(private val db: FavoriteDao) : FavoriteRepository {

    override val dbService = db
    override val webService = null

    override fun addFavorite(favoriteEntity: FavoriteEntity): Completable {
        return Completable.fromAction {
            dbService.save(favoriteEntity)
        }
    }

    override fun getFavorites(): Single<List<FavoriteEntity>> {
        return dbService.getList()
    }

    override fun get(slug: String): Single<FavoriteEntity> {
        return dbService.get(slug)
    }

    override fun remove(favoriteEntity: FavoriteEntity): Completable {
        return Completable.fromAction {
            dbService.remove(favoriteEntity)
        }
    }
}