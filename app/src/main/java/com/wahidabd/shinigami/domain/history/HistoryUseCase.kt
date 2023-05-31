package com.wahidabd.shinigami.domain.history

import com.wahidabd.shinigami.domain.history.model.History
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 5/31/2023.
 * Github github.com/wahidabd.
 */


interface HistoryUseCase {
    fun save(historyEntity: History): Completable
    fun list(): Single<List<History>>
    fun get(slug: String): Single<History>
    fun update(historyEntity: History): Completable
    fun remove(historyEntity: History): Completable
}