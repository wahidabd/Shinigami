package com.wahidabd.shinigami.presentation.comic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BasePagingRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import com.wahidabd.shinigami.databinding.ItemComicBinding
import com.wahidabd.shinigami.domain.home.model.Komik


/**
 * Created by Wahid on 4/12/2023.
 * Github github.com/wahidabd.
 */


class ComicPagingAdapter(
    private val context: Context,
    private val onItemClicked: ((String) -> Unit)?
) : BasePagingRecyclerAdapter<Komik, ComicPagingAdapter.ViewHolder>() {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemComicBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(getViewBinding(parent, viewType))
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) 0
        else 1
    }

    inner class ViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<Komik>(binding) {
        override fun bind(data: Komik) {
            with(binding as ItemComicBinding) {
                imgAvatar.setImageUrl(context, data.cover)
                tvTitle.text = data.title

                comicContainer.onClick {
                    onItemClicked?.invoke(data.slug)
                }
            }
        }
    }
}
