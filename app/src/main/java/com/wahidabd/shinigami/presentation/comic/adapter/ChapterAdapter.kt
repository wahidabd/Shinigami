package com.wahidabd.shinigami.presentation.comic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.shinigami.databinding.ItemChapterBinding
import com.wahidabd.shinigami.domain.comic.model.Chapter
import com.wahidabd.shinigami.domain.comic.model.ComicDetail


/**
 * Created by Wahid on 4/30/2023.
 * Github github.com/wahidabd.
 */


class ChapterAdapter(
    private val context: Context,
    items: ArrayList<Chapter> = arrayListOf(),
    private val onItemClicked: ((String) -> Unit)?
) : BaseAsyncRecyclerAdapter<Chapter, ChapterAdapter.ViewHolder>(items) {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemChapterBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterAdapter.ViewHolder {
        return ViewHolder(getViewBinding(parent, viewType))
    }

    inner class ViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<Chapter>(binding){
        override fun bind(data: Chapter) {
            (binding as ItemChapterBinding).apply {
                tvTitle.text = data.title
                tvTime.text = data.time

                root.onClick {
                    onItemClicked?.invoke(data.slug.toString())
                }
            }
        }
    }

}