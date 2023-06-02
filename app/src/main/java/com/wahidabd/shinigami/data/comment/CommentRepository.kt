package com.wahidabd.shinigami.data.comment

import com.wahidabd.shinigami.data.comment.model.CommentItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 6/2/2023.
 * Github github.com/wahidabd.
 */


interface CommentRepository {
    fun add(data: CommentItem): Single<Boolean>
//    fun update(data: CommentItem): Single<Boolean>
    fun list(slug: String): Single<List<CommentItem>>
}