package com.wahidabd.shinigami.di.feature

import com.wahidabd.shinigami.data.comment.CommentDataSource
import com.wahidabd.shinigami.data.comment.CommentRepository
import com.wahidabd.shinigami.domain.comment.CommentInteractor
import com.wahidabd.shinigami.domain.comment.CommentUseCase
import com.wahidabd.shinigami.presentation.comment.CommentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by Wahid on 6/2/2023.
 * Github github.com/wahidabd.
 */


val commentModule = module {
    single<CommentRepository> { CommentDataSource() }
    single<CommentUseCase> { CommentInteractor(get()) }
    viewModel { CommentViewModel(get(), get()) }
}