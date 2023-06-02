package com.wahidabd.shinigami.utils.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug


/**
 * Created by Wahid on 6/2/2023.
 * Github github.com/wahidabd.
 */


abstract class FirebaseManager {
    private val database = FirebaseDatabase.getInstance()
    protected abstract val databaseRef: DatabaseReference

    fun setValue(
        value: Any,
        child: String,
        onSuccess: (() -> Unit)? = null,
        onError: ((error: Exception) -> Unit)? = null
    ) {
        databaseRef.child(child).setValue(value)
            .addOnSuccessListener { onSuccess?.invoke() }
            .addOnFailureListener { error -> onError?.invoke(error) }
    }

    fun updateChildren(
        values: Map<String, Any>,
        onSuccess: (() -> Unit)? = null,
        onError: ((error: Exception) -> Unit)? = null
    ) {
        databaseRef.updateChildren(values)
            .addOnSuccessListener { onSuccess?.invoke() }
            .addOnFailureListener { error -> onError?.invoke(error) }
    }

    fun removeValue(
        onSuccess: (() -> Unit)? = null,
        onError: ((error: Exception) -> Unit)? = null
    ) {
        databaseRef.removeValue()
            .addOnSuccessListener { onSuccess?.invoke() }
            .addOnFailureListener { error -> onError?.invoke(error) }
    }

    fun <T> addValueEventListener(
        clazz: Class<T>,
        child: String,
        onSuccess: ((data: List<T>) -> Unit)? = null,
        onError: ((error: Exception) -> Unit)? = null
    ) {
        try {
            databaseRef.child(child).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = ArrayList<T>()

                    for (i in snapshot.children) {
                        val data = i.getValue(clazz)
                        if (data != null) list.add(data)
                    }

                    onSuccess?.invoke(list)
                }

                override fun onCancelled(error: DatabaseError) {
                    onError?.invoke(error.toException())
                }
            })
        }catch (e: Exception){
            onError?.invoke(e)
        }
    }

}