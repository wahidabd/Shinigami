package com.wahidabd.shinigami.presentation.comic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.wahidabd.library.data.Resource
import com.wahidabd.library.presentation.BaseViewModel
import com.wahidabd.library.utils.exts.addTo
import com.wahidabd.library.utils.rx.apihandlers.genericErrorHandler
import com.wahidabd.library.utils.rx.transformers.observerScheduler
import com.wahidabd.shinigami.domain.comic.ComicUseCase
import com.wahidabd.shinigami.domain.home.model.Komik
import io.reactivex.rxjava3.disposables.CompositeDisposable


/**
 * Created by Wahid on 4/12/2023.
 * Github github.com/wahidabd.
 */


class ComicViewModel(
    private val useCase: ComicUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {

    private val _comic = MutableLiveData<PagingData<Komik>>()
    val comic: LiveData<PagingData<Komik>> get() = _comic

    fun comic(order: String) {
        useCase.getPaging(order)
            .compose(observerScheduler())
            .subscribe { _comic.value = it }
            .addTo(disposable)
    }
}