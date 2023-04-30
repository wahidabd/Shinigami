package com.wahidabd.shinigami.presentation.comic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.shinigami.databinding.ItemGenreBinding


/**
 * Created by Wahid on 4/30/2023.
 * Github github.com/wahidabd.
 */


class GenreAdapter(
    private val context: Context,
    items: List<String> = mutableListOf()
) : BaseAsyncRecyclerAdapter<String, GenreAdapter.ViewHolder>(items) {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemGenreBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapter.ViewHolder {
        return ViewHolder(getViewBinding(parent, viewType))
    }

    inner class ViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<String>(binding){
        override fun bind(data: String) {
            (binding as ItemGenreBinding).apply {
                tvValue.text = data
            }
        }
    }

}