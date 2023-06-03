package com.wahidabd.shinigami.domain.comment

import com.wahidabd.shinigami.domain.comment.model.Comment
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 6/2/2023.
 * Github github.com/wahidabd.
 */


interface CommentUseCase {
    fun add(data: Comment): Single<Boolean>
//    fun update(data: CommentItem): Single<Boolean>
    fun list(slug: String): Observable<List<Comment>>
}