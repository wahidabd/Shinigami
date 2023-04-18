package com.wahidabd.shinigami.presentation.comic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.shinigami.databinding.LayoutLoadStateAdapterBinding


/**
 * Created by Wahid on 4/18/2023.
 * Github github.com/wahidabd.
 */

class ComicLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ComicLoadStateAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ComicLoadStateAdapter.ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ComicLoadStateAdapter.ViewHolder {
        val binding = LayoutLoadStateAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(
        private val binding: LayoutLoadStateAdapterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.apply {
                if (loadState is LoadState.Error) tvError.text = loadState.error.message
                progressBar.isVisible = loadState is LoadState.Loading
                tvError.isVisible = loadState is LoadState.Error
                btnRetry.isVisible = loadState is LoadState.Error

                btnRetry.onClick {
                    retry.invoke()
                }
            }
        }
    }

}