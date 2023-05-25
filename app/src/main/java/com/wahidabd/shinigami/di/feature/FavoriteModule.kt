package com.wahidabd.shinigami.di.feature

import com.wahidabd.shinigami.data.AppDatabase
import com.wahidabd.shinigami.data.favorite.FavoriteDataSource
import com.wahidabd.shinigami.data.favorite.FavoriteRepository
import com.wahidabd.shinigami.domain.favorite.FavoriteInteractor
import com.wahidabd.shinigami.domain.favorite.FavoriteUseCase
import com.wahidabd.shinigami.presentation.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by Wahid on 5/26/2023.
 * Github github.com/wahidabd.
 */


val favoriteModule = module {

    single {
        val db: AppDatabase = get()
        return@single db.favoriteDao()
    }

    single<FavoriteRepository> { FavoriteDataSource(get()) }
    single<FavoriteUseCase> { FavoriteInteractor(get()) }
    viewModel { FavoriteViewModel(get(), get()) }

}