package com.wahidabd.shinigami.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import com.wahidabd.shinigami.databinding.ItemCardComicBinding
import com.wahidabd.shinigami.domain.home.model.Comic


/**
 * Created by Wahid on 4/12/2023.
 * Github github.com/wahidabd.
 */


class TrendingAdapter(
    private val context: Context,
    items: List<Comic> = mutableListOf(),
    private val onItemClicked: ((String) -> Unit)?
) : BaseAsyncRecyclerAdapter<Comic, TrendingAdapter.ViewHolder>(items) {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemCardComicBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingAdapter.ViewHolder {
        return ViewHolder(getViewBinding(parent, viewType))
    }

    inner class ViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<Comic>(binding) {
        override fun bind(data: Comic) {
            with(binding as ItemCardComicBinding) {
                tvTitle.text = data.title
                imgCover.setImageUrl(context, data.cover, isCenterCrop = true)

                root.onClick {
                    onItemClicked?.invoke(data.slug)
                }

            }
        }
    }

}