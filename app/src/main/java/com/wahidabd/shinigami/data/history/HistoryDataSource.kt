package com.wahidabd.shinigami.data.history

import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.WebApi
import com.wahidabd.shinigami.data.history.dao.HistoryDao
import com.wahidabd.shinigami.data.history.dao.HistoryEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 5/31/2023.
 * Github github.com/wahidabd.
 */


class HistoryDataSource(private val db: HistoryDao) : HistoryRepository {

    override val dbService = db
    override val webService: WebApi? = null

    override fun save(historyEntity: HistoryEntity): Completable {
        return Completable.fromAction {
            dbService.save(historyEntity)
        }
    }

    override fun list(): Single<List<HistoryEntity>> {
        return dbService.getList()
    }

    override fun get(slug: String): Single<HistoryEntity> {
        return dbService.get(slug)
    }

    override fun update(historyEntity: HistoryEntity): Completable {
        return Completable.fromAction {
            dbService.update(historyEntity)
        }
    }

    override fun remove(historyEntity: HistoryEntity): Completable {
        return Completable.fromAction {
            dbService.remove(historyEntity)
        }
    }


}