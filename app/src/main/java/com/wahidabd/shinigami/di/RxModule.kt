package com.wahidabd.shinigami.di

import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.dsl.module


/**
 * Created by Wahid on 4/7/2023.
 * Github github.com/wahidabd.
 */


val rxModule = module {
    factory { CompositeDisposable() }
}