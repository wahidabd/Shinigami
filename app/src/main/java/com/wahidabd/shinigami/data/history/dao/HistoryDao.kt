package com.wahidabd.shinigami.data.history.dao

import androidx.room.Dao
import androidx.room.Query
import com.wahidabd.library.data.RxLocalDb
import com.wahidabd.shinigami.data.favorite.dao.FavoriteEntity
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 5/31/2023.
 * Github github.com/wahidabd.
 */


@Dao
abstract class HistoryDao : RxLocalDb<HistoryEntity> {

    @Query("SELECT * FROM history")
    abstract override fun getList(): Single<List<HistoryEntity>>

    @Query("SELECT * FROM history WHERE slug = :slug")
    abstract fun get(slug: String): Single<HistoryEntity>

    @Query("SELECT * FROM history WHERE title = :intId")
    abstract override fun get(intId: Int?): Single<HistoryEntity>

    @Query("DELETE FROM history WHERE title = :intId")
    abstract override fun remove(intId: Int?)

}