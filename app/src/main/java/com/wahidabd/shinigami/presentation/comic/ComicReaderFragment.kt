package com.wahidabd.shinigami.presentation.comic

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.shinigami.R
import com.wahidabd.shinigami.databinding.FragmentComicReaderBinding
import com.wahidabd.shinigami.presentation.comic.adapter.ReaderAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicReaderFragment : BaseFragment<FragmentComicReaderBinding>() {

    private val viewModel: ComicViewModel by viewModel()
    private val args: ComicReaderFragmentArgs by navArgs()

    private val mAdapter by lazy {
        ReaderAdapter(
            requireContext(),
            onClicked = {}
        )
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentComicReaderBinding {
        return FragmentComicReaderBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        with(binding) {
            rvReader.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(requireContext())
                itemAnimator = DefaultItemAnimator()
            }

            onScrollListener()
        }
    }

    override fun initAction() {
        binding.apply {
            toolbar.setEnableBack { findNavController().navigateUp() }
        }
    }

    override fun initProcess() {
        viewModel.reader(args.chapter)
    }

    override fun initObservers() {
        viewModel.reader.observerLiveData(
            viewLifecycleOwner,
            onEmpty = {},
            onLoading = {},
            onFailure = { _, message ->
                showToast(message.toString())
            },
            onSuccess = {
                binding.toolbar.setTitle(it.title.toString())
                mAdapter.setData = it.items
            },
        )
    }

    private fun onScrollListener(){
        with(binding){
            rvReader.apply {
                addOnScrollListener(object : RecyclerView.OnScrollListener(){
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (recyclerView.scrollState == RecyclerView.SCROLL_STATE_SETTLING){
                            when {
                                dy > 0 -> {
                                    toolbar.gone()
                                }
                                dy < 0 -> {
                                    toolbar.visible()
                                }
                            }
                        }
                    }
                })
            }
        }
    }

}