package com.wahidabd.shinigami.domain.favorite

import com.wahidabd.shinigami.data.favorite.FavoriteRepository
import com.wahidabd.shinigami.domain.favorite.model.Favorite
import com.wahidabd.shinigami.domain.favorite.model.toDomain
import com.wahidabd.shinigami.domain.favorite.model.toEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 5/25/2023.
 * Github github.com/wahidabd.
 */


class FavoriteInteractor(private val repository: FavoriteRepository): FavoriteUseCase {
    override fun addFavorite(favoriteEntity: Favorite): Completable {
        return repository.addFavorite(favoriteEntity.toEntity())
    }

    override fun getFavorites(): Single<List<Favorite>> {
        return repository.getFavorites().map {
            it.map { favoriteEntity ->
                favoriteEntity.toDomain()
            }
        }
    }

    override fun get(slug: String): Single<Favorite> {
        return repository.get(slug).map {
            it.toDomain()
        }
    }

    override fun remove(favoriteEntity: Favorite): Completable {
        return repository.remove(favoriteEntity.toEntity())
    }
}