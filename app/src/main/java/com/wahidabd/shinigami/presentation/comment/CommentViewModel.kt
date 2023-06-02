package com.wahidabd.shinigami.presentation.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahidabd.library.data.Resource
import com.wahidabd.library.presentation.BaseViewModel
import com.wahidabd.library.utils.exts.addTo
import com.wahidabd.library.utils.rx.apihandlers.genericErrorHandler
import com.wahidabd.library.utils.rx.transformers.singleScheduler
import com.wahidabd.shinigami.domain.comment.CommentUseCase
import com.wahidabd.shinigami.domain.comment.model.Comment
import io.reactivex.rxjava3.disposables.CompositeDisposable


/**
 * Created by Wahid on 6/2/2023.
 * Github github.com/wahidabd.
 */


class CommentViewModel(
    private val useCase: CommentUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {

    private val _add = MutableLiveData<Resource<Boolean>>()
    val add: LiveData<Resource<Boolean>> get() = _add

    private val _list = MutableLiveData<Resource<List<Comment>>>()
    val list: LiveData<Resource<List<Comment>>> get() = _list

    init {
        _add.value = Resource.default()
        _list.value = Resource.default()
    }

    fun add(data: Comment) {
        _add.value = Resource.loading()

        useCase.add(data).compose(singleScheduler())
            .subscribe({
                _add.value = Resource.success(it)
            }, { genericErrorHandler(it, _add) })
            .addTo(disposable)
    }

    fun list(slug: String) {
        _list.value = Resource.loading()

        useCase.list(slug).compose(singleScheduler())
            .subscribe({
                if (it.isEmpty()) _list.value = Resource.empty()
                else _list.value = Resource.success(it)
            }, { genericErrorHandler(it, _list) })
            .addTo(disposable)
    }

}