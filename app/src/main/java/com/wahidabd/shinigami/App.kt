package com.wahidabd.shinigami

import com.wahidabd.library.presentation.BaseApplication
import com.wahidabd.shinigami.di.comicModule
import com.wahidabd.shinigami.di.homeModule
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
            homeModule,
            comicModule
        )
    }

    override fun initApp() {
        Timber.plant(Timber.DebugTree())
    }
}