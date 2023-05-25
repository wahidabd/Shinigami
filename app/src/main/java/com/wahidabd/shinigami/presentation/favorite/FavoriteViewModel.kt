package com.wahidabd.shinigami.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahidabd.library.data.Resource
import com.wahidabd.library.presentation.BaseViewModel
import com.wahidabd.library.utils.exts.addTo
import com.wahidabd.library.utils.rx.apihandlers.genericErrorHandler
import com.wahidabd.library.utils.rx.transformers.completableScheduler
import com.wahidabd.library.utils.rx.transformers.singleScheduler
import com.wahidabd.shinigami.domain.favorite.FavoriteUseCase
import com.wahidabd.shinigami.domain.favorite.model.Favorite
import io.reactivex.rxjava3.disposables.CompositeDisposable


/**
 * Created by Wahid on 5/25/2023.
 * Github github.com/wahidabd.
 */


class FavoriteViewModel(
    private val useCase: FavoriteUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {

    private val _add = MutableLiveData<Resource<Unit>>()
    val add: LiveData<Resource<Unit>> get() = _add

    private val _getFavorites = MutableLiveData<Resource<List<Favorite>>>()
    val getFavorites: LiveData<Resource<List<Favorite>>> get() = _getFavorites

    private val _get = MutableLiveData<Resource<Favorite>>()
    val get: LiveData<Resource<Favorite>> get() = _get

    private val _remove = MutableLiveData<Resource<Unit>>()
    val remove: LiveData<Resource<Unit>> get() = _remove

    init {
        _add.value = Resource.default()
        _getFavorites.value = Resource.default()
        _remove.value = Resource.default()
        _get.value = Resource.default()
    }

    fun add(favorite: Favorite){
        _add.value = Resource.loading()

        useCase.addFavorite(favorite)
            .compose(completableScheduler<Unit>())
            .subscribe({
                _add.value = Resource.success(Unit)
            }, { genericErrorHandler(it, _add) })
            .addTo(disposable)
    }

    fun remove(favorite: Favorite){
        _remove.value = Resource.loading()

        useCase.remove(favorite)
            .compose(completableScheduler<Unit>())
            .subscribe({
                _remove.value = Resource.success(Unit)
            }, { genericErrorHandler(it, _remove) })
            .addTo(disposable)
    }

    fun getFavorites(){
        _getFavorites.value = Resource.loading()

        useCase.getFavorites()
            .compose(singleScheduler())
            .subscribe({
                if (it.isEmpty()) _getFavorites.value = Resource.empty()
                else _getFavorites.value = Resource.success(it)
            }, { genericErrorHandler(it, _getFavorites) })
            .addTo(disposable)
    }

    fun get(slug: String){
        _get.value = Resource.loading()

        useCase.get(slug)
            .compose(singleScheduler())
            .subscribe({
                if (it == null) _get.value = Resource.empty()
                else _get.value = Resource.success(it)
            }, { genericErrorHandler(it, _get) })
            .addTo(disposable)
    }

}