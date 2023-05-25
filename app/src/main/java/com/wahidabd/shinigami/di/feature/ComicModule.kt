package com.wahidabd.shinigami.di.feature

import com.wahidabd.shinigami.data.comic.ComicDataSource
import com.wahidabd.shinigami.data.comic.ComicPagingSource
import com.wahidabd.shinigami.data.comic.ComicRepository
import com.wahidabd.shinigami.domain.comic.ComicInteractor
import com.wahidabd.shinigami.domain.comic.ComicUseCase
import com.wahidabd.shinigami.presentation.comic.ComicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by Wahid on 4/12/2023.
 * Github github.com/wahidabd.
 */


val comicModule = module {
    single { ComicPagingSource() }
    single<ComicRepository> { ComicDataSource() }
    single<ComicUseCase> { ComicInteractor(get()) }
    viewModel { ComicViewModel(get(), get()) }
}