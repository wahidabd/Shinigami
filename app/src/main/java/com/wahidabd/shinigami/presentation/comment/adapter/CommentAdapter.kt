package com.wahidabd.shinigami.presentation.comment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onLongClick
import com.wahidabd.shinigami.databinding.ItemCommentBinding
import com.wahidabd.shinigami.domain.comment.model.Comment
import com.wahidabd.shinigami.utils.circularProgress
import com.wahidabd.shinigami.utils.setImageReader


/**
 * Created by Wahid on 6/2/2023.
 * Github github.com/wahidabd.
 */


class CommentAdapter(
    private val context: Context,
    items: ArrayList<Comment> = arrayListOf(),
    private val onLongItemClick: ((String) -> Unit)
) : BaseAsyncRecyclerAdapter<Comment, CommentAdapter.ViewHolder>(items) {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemCommentBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.ViewHolder {
        return ViewHolder(getViewBinding(parent, viewType))
    }

    inner class ViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<Comment>(binding) {
        override fun bind(data: Comment) = with(binding as ItemCommentBinding) {
            val circular = context.circularProgress()
            tvName.text = data.name
            tvComment.text = data.body
            imgAvatar.setImageReader(data.avatar.toString(), circular)

            root.onLongClick {
                onLongItemClick.invoke(data.id.toString())
                true
            }
        }
    }
}