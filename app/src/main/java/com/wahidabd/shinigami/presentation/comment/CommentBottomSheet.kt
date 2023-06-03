package com.wahidabd.shinigami.presentation.comment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.wahidabd.library.presentation.fragment.BaseBottomSheetDialogFragment
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showLoadingState
import com.wahidabd.library.utils.exts.clear
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.shinigami.R
import com.wahidabd.shinigami.databinding.LayoutCommentBottomSheetBinding
import com.wahidabd.shinigami.domain.comment.model.Comment
import com.wahidabd.shinigami.presentation.comment.adapter.CommentAdapter
import com.wahidabd.shinigami.utils.currentTimestamp
import com.wahidabd.shinigami.utils.pref.PreferenceManager
import com.wahidabd.shinigami.utils.randomUUID
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Wahid on 6/2/2023.
 * Github github.com/wahidabd.
 */


class CommentBottomSheet : BaseBottomSheetDialogFragment<LayoutCommentBottomSheetBinding>() {

    override val bottomSheetTheme: Int = R.style.BottomDialogTheme
    override val tagName: String = CommentBottomSheet::class.java.name

    private val viewModel: CommentViewModel by viewModel()
    private val pref: PreferenceManager by inject()
    private var slug = emptyString()

    private val mAdapter by lazy {
        CommentAdapter(
            requireContext(),
            onLongItemClick = {}
        )
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): LayoutCommentBottomSheetBinding {
        return LayoutCommentBottomSheetBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        slug = arguments?.getString(KEY_SLUG).toString()

        debug { "SLUG = $slug" }

        binding.rvComment.apply {
            adapter = mAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
            itemAnimator = DefaultItemAnimator()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initAction() {
        with(binding) {

            imgSend.onClick {
                val user = pref.getUser()
                val body = edComment.toStringTrim()

                val comment = Comment(
                    id = requireContext().randomUUID(),
                    name = user.name,
                    avatar = user.avatar,
                    body = body,
                    date = currentTimestamp(),
                    slug = slug
                )

                if (body.isNotEmpty()) sendComment(comment)
                else showToast("Message is required")
            }

        }
    }

    override fun initProcess() {
        viewModel.list(slug)
    }

    override fun initObservers() {
        with(binding) {
            viewModel.list.observerLiveData(
                viewLifecycleOwner,
                onEmpty = {
                    msv.viewState = MultiStateView.ViewState.EMPTY
                    debug { "EMPTY" }
                },
                onLoading = {
                    msv.showLoadingState()
                },
                onFailure = { _, message ->
                    msv.showDefaultState()
                    showToast(message.toString())
                },
                onSuccess = {
                    msv.showDefaultState()
                    mAdapter.setData = it
                }
            )
        }
    }

    private fun sendComment(comment: Comment) {
        viewModel.add(comment)
        viewModel.add.observerLiveData(
            viewLifecycleOwner,
            onEmpty = {},
            onLoading = {
            },
            onFailure = { _, _ -> },
            onSuccess = {
                binding.msv.showDefaultState()
                binding.edComment.clear()
                viewModel.list(slug)
            }
        )
    }

    companion object {
        private const val KEY_SLUG = "key_slug"

        @JvmStatic
        fun newInstance(slug: String): CommentBottomSheet {
            return CommentBottomSheet().apply {
                arguments = Bundle().apply {
                    putString(KEY_SLUG, slug)
                }
            }
        }
    }

}