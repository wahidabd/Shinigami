package com.wahidabd.shinigami.data.history

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.shinigami.data.history.dao.HistoryEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 5/31/2023.
 * Github github.com/wahidabd.
 */


interface HistoryRepository : BaseRepository {

    fun save(historyEntity: HistoryEntity): Completable
    fun list(): Single<List<HistoryEntity>>
    fun get(slug: String): Single<HistoryEntity>
    fun update(historyEntity: HistoryEntity): Completable
    fun remove(historyEntity: HistoryEntity): Completable

}