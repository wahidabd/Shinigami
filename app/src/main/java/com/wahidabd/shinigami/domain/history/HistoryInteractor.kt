package com.wahidabd.shinigami.domain.history

import android.os.Build
import androidx.annotation.RequiresApi
import com.wahidabd.shinigami.data.history.HistoryRepository
import com.wahidabd.shinigami.domain.history.model.History
import com.wahidabd.shinigami.domain.history.model.toDomain
import com.wahidabd.shinigami.domain.history.model.toEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 5/31/2023.
 * Github github.com/wahidabd.
 */


class HistoryInteractor(private val repository: HistoryRepository): HistoryUseCase{

    override fun save(historyEntity: History): Completable {
        return repository.save(historyEntity.toEntity())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun list(): Single<List<History>> {
        return repository.list().map {
            it.map { historyEntity ->
                historyEntity.toDomain()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun get(slug: String): Single<History> {
        return repository.get(slug).map {
            it.toDomain()
        }
    }

    override fun update(historyEntity: History): Completable {
        return repository.update(historyEntity.toEntity())
    }

    override fun remove(historyEntity: History): Completable {
        return repository.remove(historyEntity.toEntity())
    }
}