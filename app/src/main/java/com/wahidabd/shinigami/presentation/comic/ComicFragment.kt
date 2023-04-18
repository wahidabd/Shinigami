package com.wahidabd.shinigami.presentation.comic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showLoadingState
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.shinigami.R
import com.wahidabd.shinigami.databinding.FragmentComicBinding
import com.wahidabd.shinigami.presentation.comic.adapter.ComicLoadStateAdapter
import com.wahidabd.shinigami.presentation.comic.adapter.ComicPagingAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicFragment : BaseFragment<FragmentComicBinding>() {

    private val args: ComicFragmentArgs by navArgs()
    private val viewModel: ComicViewModel by viewModel()

    private val mAdapter by lazy {
        ComicPagingAdapter(
            requireContext(),
            onItemClicked = {}
        )
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentComicBinding {
        return FragmentComicBinding.inflate(layoutInflater)
    }

    override fun initUI() {

        val gridManager = GridLayoutManager(requireContext(), 2).apply {
            this.spanSizeLookup =
                object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        val viewType = mAdapter.getItemViewType(position)
                        return if (viewType == 1) 1 else 2
                    }
                }
        }

        binding.tvToolbar.apply {
            title = args.title
            setNavigationIcon(R.drawable.ic_arrow_left)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }

        binding.rvComic.apply {
            adapter = mAdapter.withLoadStateHeaderAndFooter(
                header = ComicLoadStateAdapter { mAdapter.retry() },
                footer = ComicLoadStateAdapter { mAdapter.retry() }
            )
            layoutManager = gridManager
        }

        loadState()
    }

    override fun initAction() {}

    override fun initProcess() {
        viewModel.comic(args.order)
    }

    override fun initObservers() {
        viewModel.comic.observe(viewLifecycleOwner) {
            binding.msvComic.showDefaultState()
            mAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun loadState() {
        mAdapter.addLoadStateListener { loadState ->
            binding.apply {
                when {
                    loadState.source.refresh is LoadState.NotLoading -> msvComic.showDefaultState()
                    loadState.source.refresh is LoadState.Loading -> msvComic.showLoadingState()
                    !(loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    mAdapter.itemCount < 1) -> msvComic.showDefaultState()
                }
            }
        }
    }

}