package com.wahidabd.shinigami.data.favorite.dao

import androidx.room.Dao
import androidx.room.Query
import com.wahidabd.library.data.RxLocalDb
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 5/25/2023.
 * Github github.com/wahidabd.
 */


@Dao
abstract class FavoriteDao : RxLocalDb<FavoriteEntity> {

    @Query("SELECT * FROM favorite")
    abstract override fun getList(): Single<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite WHERE slug = :slug")
    abstract fun get(slug: String): Single<FavoriteEntity>

    @Query("SELECT * FROM favorite WHERE title = :intId")
    abstract override fun get(intId: Int?): Single<FavoriteEntity>

    @Query("DELETE FROM favorite WHERE title = :intId")
    abstract override fun remove(intId: Int?)
}