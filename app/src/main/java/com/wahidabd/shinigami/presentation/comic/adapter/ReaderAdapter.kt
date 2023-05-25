package com.wahidabd.shinigami.presentation.comic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.setImageUrl
import com.wahidabd.shinigami.databinding.ItemReaderBinding
import com.wahidabd.shinigami.domain.comic.model.ContentReader
import com.wahidabd.shinigami.utils.circularProgress
import com.wahidabd.shinigami.utils.setImageReader


/**
 * Created by Wahid on 5/23/2023.
 * Github github.com/wahidabd.
 */


class ReaderAdapter(
    private val context: Context,
    items: List<ContentReader> = mutableListOf()
) : BaseAsyncRecyclerAdapter<ContentReader, ReaderAdapter.ViewHolder>(items) {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemReaderBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReaderAdapter.ViewHolder {
        return ViewHolder(
            getViewBinding(parent, viewType)
        )
    }

    inner class ViewHolder(
        binding: ViewBinding
    ) : BaseAsyncItemViewHolder<ContentReader>(binding) {
        override fun bind(data: ContentReader) {
            with(binding as ItemReaderBinding) {
                val progress = context.circularProgress()
                imgSource.setImageReader(data.image, progress)
                debug { data.image }
            }
        }
    }
}