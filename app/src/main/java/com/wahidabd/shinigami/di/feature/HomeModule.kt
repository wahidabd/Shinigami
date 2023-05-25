package com.wahidabd.shinigami.di.feature

import com.wahidabd.shinigami.data.home.HomeDataSource
import com.wahidabd.shinigami.data.home.HomeRepository
import com.wahidabd.shinigami.domain.home.HomeInteractor
import com.wahidabd.shinigami.domain.home.HomeUseCase
import com.wahidabd.shinigami.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by Wahid on 4/7/2023.
 * Github github.com/wahidabd.
 */


val homeModule = module {
    single<HomeRepository> { HomeDataSource() }
    single<HomeUseCase> { HomeInteractor(get()) }
    viewModel { HomeViewModel(get(), get()) }
}