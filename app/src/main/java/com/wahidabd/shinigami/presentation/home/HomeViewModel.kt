package com.wahidabd.shinigami.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahidabd.library.data.Resource
import com.wahidabd.library.presentation.BaseViewModel
import com.wahidabd.library.utils.exts.addTo
import com.wahidabd.library.utils.rx.apihandlers.genericErrorHandler
import com.wahidabd.library.utils.rx.transformers.singleScheduler
import com.wahidabd.shinigami.domain.home.HomeUseCase
import com.wahidabd.shinigami.domain.home.model.Comic
import com.wahidabd.shinigami.utils.Quadruple
import io.reactivex.rxjava3.disposables.CompositeDisposable


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */


class HomeViewModel(
    private val useCase: HomeUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {


    private val _homeData =
        MutableLiveData<Resource<Quadruple<List<Comic>, List<Comic>, List<Comic>, List<Comic>>>>()
    val homeData: LiveData<Resource<Quadruple<List<Comic>, List<Comic>, List<Comic>, List<Comic>>>> get() = _homeData

    init {
        _homeData.value = Resource.default()
    }

    fun homeData() {
        _homeData.value = Resource.loading()

        useCase.getHomeData()
            .compose(singleScheduler())
            .subscribe({
                _homeData.value = Resource.success(it)
            }, { genericErrorHandler(it, _homeData) })
            .addTo(disposable)
    }

}