package com.wahidabd.shinigami.presentation.comic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.wahidabd.library.data.Resource
import com.wahidabd.library.presentation.BaseViewModel
import com.wahidabd.library.utils.exts.addTo
import com.wahidabd.library.utils.rx.apihandlers.genericErrorHandler
import com.wahidabd.library.utils.rx.transformers.observerScheduler
import com.wahidabd.library.utils.rx.transformers.singleScheduler
import com.wahidabd.shinigami.domain.comic.ComicUseCase
import com.wahidabd.shinigami.domain.comic.model.ComicDetail
import com.wahidabd.shinigami.domain.comic.model.Reader
import com.wahidabd.shinigami.domain.home.model.Comic
import io.reactivex.rxjava3.disposables.CompositeDisposable


/**
 * Created by Wahid on 4/12/2023.
 * Github github.com/wahidabd.
 */


class ComicViewModel(
    private val useCase: ComicUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {

    private val _comic = MutableLiveData<PagingData<Comic>>()
    val comic: LiveData<PagingData<Comic>> get() = _comic

    private val _detail = MutableLiveData<Resource<ComicDetail>>()
    val detail: LiveData<Resource<ComicDetail>> get() = _detail

    private val _reader = MutableLiveData<Resource<Reader>>()
    val reader: LiveData<Resource<Reader>> get() = _reader

    init {
        _detail.value = Resource.default()
        _reader.value = Resource.default()
    }


    fun comic(order: String) {
        useCase.getPaging(order)
            .compose(observerScheduler())
            .subscribe { _comic.value = it }
            .addTo(disposable)
    }

    fun detail(slug: String) {
        _detail.value = Resource.loading()

        useCase.getDetail(slug).compose(singleScheduler())
            .subscribe({
                _detail.value = Resource.success(it)
            }, { genericErrorHandler(it, _detail) })
            .addTo(disposable)
    }

    fun reader(slug: String, chapter: String) {
        _reader.value = Resource.loading()

        useCase.reader(slug, chapter)
            .compose(singleScheduler())
            .subscribe({
                _reader.value = Resource.success(it)
            }, { genericErrorHandler(it, _reader) })
            .addTo(disposable)
    }
}