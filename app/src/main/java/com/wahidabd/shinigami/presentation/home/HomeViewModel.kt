package com.wahidabd.shinigami.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahidabd.library.data.Resource
import com.wahidabd.library.presentation.BaseViewModel
import com.wahidabd.library.utils.exts.addTo
import com.wahidabd.library.utils.rx.apihandlers.genericErrorHandler
import com.wahidabd.library.utils.rx.transformers.singleScheduler
import com.wahidabd.shinigami.domain.home.HomeUseCase
import com.wahidabd.shinigami.domain.home.model.Komik
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

    private val _popularLatest = MutableLiveData<Resource<Pair<List<Komik>, List<Komik>>>>()
    val popularLatest: LiveData<Resource<Pair<List<Komik>, List<Komik>>>> get() = _popularLatest

    private val _trendingMirror = MutableLiveData<Resource<Pair<List<Komik>, List<Komik>>>>()
    val trendingMirror: LiveData<Resource<Pair<List<Komik>, List<Komik>>>> get() = _trendingMirror

    private val _homeData = MutableLiveData<Resource<Quadruple<List<Komik>, List<Komik>, List<Komik>, List<Komik>>>>()
    val homeData: LiveData<Resource< Quadruple<List<Komik>, List<Komik>, List<Komik>, List<Komik>>>> get() = _homeData

    init {
        _homeData.value = Resource.default()
        _popularLatest.value = Resource.default()
        _trendingMirror.value = Resource.default()
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

    fun popularLatest() {
        _popularLatest.value = Resource.loading()

        useCase.popularLatest()
            .compose(singleScheduler())
            .subscribe({
                _popularLatest.value = Resource.success(it)
            }, { genericErrorHandler(it, _popularLatest) })
            .addTo(disposable)
    }

    fun trendingMirror() {
        _trendingMirror.value = Resource.loading()

        useCase.trendingMirror()
            .compose(singleScheduler())
            .subscribe({
                _trendingMirror.value = Resource.success(it)
            }, { genericErrorHandler(it, _trendingMirror) })
            .addTo(disposable)
    }

}