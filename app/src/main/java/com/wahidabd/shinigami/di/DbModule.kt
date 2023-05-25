package com.wahidabd.shinigami.di

import com.wahidabd.shinigami.data.AppDatabase
import org.koin.dsl.module


/**
 * Created by Wahid on 5/26/2023.
 * Github github.com/wahidabd.
 */


val dbModule = module {
    single { AppDatabase.getDatabase(get()) }
}