package com.wahidabd.shinigami.domain.comment

import com.wahidabd.shinigami.data.comment.CommentRepository
import com.wahidabd.shinigami.domain.comment.model.Comment
import com.wahidabd.shinigami.domain.comment.model.toDomain
import com.wahidabd.shinigami.domain.comment.model.toResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 6/2/2023.
 * Github github.com/wahidabd.
 */


class CommentInteractor(private val repository: CommentRepository) : CommentUseCase {

    override fun add(data: Comment): Single<Boolean> {
        return repository.add(data.toResponse())
    }

    override fun list(slug: String): Observable<List<Comment>> {
        return repository.list(slug).map {
            it.map { commentItem ->
                commentItem.toDomain()
            }
        }
    }

}