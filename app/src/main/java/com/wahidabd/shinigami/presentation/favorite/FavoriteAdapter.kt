package com.wahidabd.shinigami.presentation.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import com.wahidabd.shinigami.databinding.ItemComicBinding
import com.wahidabd.shinigami.domain.favorite.model.Favorite


/**
 * Created by Wahid on 5/26/2023.
 * Github github.com/wahidabd.
 */


class FavoriteAdapter(
    private val context: Context,
    items: List<Favorite> = mutableListOf(),
    private val onItemClicked: ((String) -> Unit)
) : BaseAsyncRecyclerAdapter<Favorite, FavoriteAdapter.ViewHolder>(items){

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemComicBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        return ViewHolder(getViewBinding(parent, viewType))
    }

    inner class ViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<Favorite>(binding){
        override fun bind(data: Favorite) = with(binding as ItemComicBinding) {
            tvTitle.text = data.title
            imgAvatar.setImageUrl(context, data.poster.toString())

            comicContainer.onClick {
                onItemClicked.invoke(data.slug.toString())
            }

        }
    }
}