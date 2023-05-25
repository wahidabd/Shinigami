package com.wahidabd.shinigami

import com.wahidabd.library.presentation.BaseApplication
import com.wahidabd.shinigami.di.dbModule
import com.wahidabd.shinigami.di.feature.comicModule
import com.wahidabd.shinigami.di.feature.favoriteModule
import com.wahidabd.shinigami.di.feature.homeModule
import com.wahidabd.shinigami.di.rxModule
import org.koin.core.module.Module
import timber.log.Timber


/**
 * Created by Wahid on 4/7/2023.
 * Github github.com/wahidabd.
 */


class App : BaseApplication() {

    override fun getDefineModule(): List<Module> {
        return listOf(
            rxModule,
            dbModule,
            homeModule,
            comicModule,
            favoriteModule
        )
    }

    override fun initApp() {
        Timber.plant(Timber.DebugTree())
    }
}