package com.wahidabd.shinigami.di

import com.wahidabd.shinigami.utils.pref.PreferenceManager
import org.koin.dsl.module


/**
 * Created by Wahid on 6/1/2023.
 * Github github.com/wahidabd.
 */


val appModule = module {
    single {
        PreferenceManager(get())
    }
}