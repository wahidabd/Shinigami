package com.wahidabd.shinigami.di.feature

import com.wahidabd.shinigami.data.AppDatabase
import com.wahidabd.shinigami.data.history.HistoryDataSource
import com.wahidabd.shinigami.data.history.HistoryRepository
import com.wahidabd.shinigami.domain.history.HistoryInteractor
import com.wahidabd.shinigami.domain.history.HistoryUseCase
import com.wahidabd.shinigami.presentation.history.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by Wahid on 5/31/2023.
 * Github github.com/wahidabd.
 */


val historyModule = module {

    single {
        val db: AppDatabase = get()
        return@single db.historyDao()
    }

    single<HistoryRepository> { HistoryDataSource(get()) }
    single<HistoryUseCase> { HistoryInteractor(get()) }
    viewModel { HistoryViewModel(get(), get()) }

}