package com.wahidabd.shinigami.data.comment

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.shinigami.data.comment.model.CommentItem
import com.wahidabd.shinigami.utils.firebase.FirebaseManager
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 6/2/2023.
 * Github github.com/wahidabd.
 */


class CommentDataSource : CommentRepository, FirebaseManager() {

    override val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference(REF)

    override fun add(data: CommentItem): Single<Boolean> {
        return Single.create { emitter ->
            setValue(
                value = data.toMap(),
                child = "${data.slug}${data.id}",
                onSuccess = {
                    emitter.onSuccess(true)
                },
                onError = {
                    emitter.onError(it)
                }
            )
        }
    }

    override fun list(slug: String): Observable<List<CommentItem>> {
        return Observable.create { emitter ->
            addValueEventListener(
                clazz = CommentItem::class.java,
                child = slug,
                onSuccess = {
                    emitter.onNext(it)
                },
                onError = {
                    emitter.onError(it)
                }
            )
        }
    }


    companion object {
        const val REF = "comment"
    }

}