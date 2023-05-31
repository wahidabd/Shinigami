package com.wahidabd.shinigami.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahidabd.library.data.Resource
import com.wahidabd.library.presentation.BaseViewModel
import com.wahidabd.library.utils.exts.addTo
import com.wahidabd.library.utils.rx.apihandlers.genericErrorHandler
import com.wahidabd.library.utils.rx.transformers.completableScheduler
import com.wahidabd.library.utils.rx.transformers.singleScheduler
import com.wahidabd.shinigami.domain.history.HistoryUseCase
import com.wahidabd.shinigami.domain.history.model.History
import io.reactivex.rxjava3.disposables.CompositeDisposable


/**
 * Created by Wahid on 5/31/2023.
 * Github github.com/wahidabd.
 */


class HistoryViewModel(
    private val useCase: HistoryUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {

    private val _list = MutableLiveData<Resource<List<History>>>()
    val list: LiveData<Resource<List<History>>> get() = _list

    private val _get = MutableLiveData<Resource<History>>()
    val get: LiveData<Resource<History>> get() = _get

    private val _save = MutableLiveData<Resource<Unit>>()
    val save: LiveData<Resource<Unit>> get() = _save

    private val _remove = MutableLiveData<Resource<Unit>>()
    val remove: LiveData<Resource<Unit>> get() = _remove

    private val _update = MutableLiveData<Resource<Unit>>()
    val update: LiveData<Resource<Unit>> get() = _update

    init {
        _list.value = Resource.default()
        _get.value = Resource.default()
        _save.value = Resource.default()
        _remove.value = Resource.default()
        _update.value = Resource.default()
    }

    fun list() {
        _list.value = Resource.loading()

        useCase.list().compose(singleScheduler())
            .subscribe({
                _list.value = Resource.success(it)
            }, { genericErrorHandler(it, _list) })
            .addTo(disposable)
    }

    fun get(slug: String) {
        _get.value = Resource.loading()

        useCase.get(slug).compose(singleScheduler())
            .subscribe({
                _get.value = Resource.success(it)
            }, { genericErrorHandler(it, _get) })
            .addTo(disposable)
    }

    fun save(history: History) {
        _save.value = Resource.loading()

        useCase.save(history).compose(completableScheduler<Unit>())
            .subscribe({
                _save.value = Resource.success(Unit)
            }, { genericErrorHandler(it, _save) })
            .addTo(disposable)
    }

    fun remove(history: History) {
        _remove.value = Resource.loading()

        useCase.remove(history).compose(completableScheduler<Unit>())
            .subscribe({
                _remove.value = Resource.success(Unit)
            }, { genericErrorHandler(it, _remove) })
            .addTo(disposable)
    }

    fun update(history: History) {
        _update.value = Resource.loading()

        useCase.update(history).compose(completableScheduler<Unit>())
            .subscribe({
                _update.value = Resource.success(Unit)
            }, { genericErrorHandler(it, _update) })
            .addTo(disposable)
    }

}