package com.wahidabd.shinigami.presentation.history.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.shinigami.R
import com.wahidabd.shinigami.databinding.ItemHistoryBinding
import com.wahidabd.shinigami.domain.history.model.History
import com.wahidabd.shinigami.utils.circularProgress
import com.wahidabd.shinigami.utils.setImageReader


/**
 * Created by Wahid on 5/31/2023.
 * Github github.com/wahidabd.
 */


class HistoryAdapter(
    private val context: Context,
    items: ArrayList<History> = arrayListOf(),
    private val onItemClicked: ((String) -> Unit),
    private val onItemRemoved: ((History) -> Unit)
) : BaseAsyncRecyclerAdapter<History, HistoryAdapter.ViewHolder>(items) {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemHistoryBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        return ViewHolder(getViewBinding(parent, viewType))
    }

    inner class ViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<History>(binding){
        override fun bind(data: History) = with(binding as ItemHistoryBinding) {
            val circular = context.circularProgress()
            imgPoster.setImageReader(data.poster.toString(), circular)
            tvTitle.text = data.title
            tvStatus.text = context.getString(R.string.format_status_history, data.chapter_title, data.date)

            container.onClick { onItemClicked.invoke(data.slug.toString()) }
            imgDelete.onClick { onItemRemoved.invoke(data) }
        }
    }
}