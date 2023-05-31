package com.wahidabd.shinigami.presentation.comic

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.shinigami.databinding.FragmentComicReaderBinding
import com.wahidabd.shinigami.domain.history.model.History
import com.wahidabd.shinigami.presentation.comic.adapter.ReaderAdapter
import com.wahidabd.shinigami.presentation.history.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicReaderFragment : BaseFragment<FragmentComicReaderBinding>() {

    private val viewModel: ComicViewModel by viewModel()
    private val historyViewModel: HistoryViewModel by viewModel()
    private val args: ComicReaderFragmentArgs by navArgs()

    private var isRead: Boolean = false
    @RequiresApi(Build.VERSION_CODES.O)
    private var history = History()

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

    @RequiresApi(Build.VERSION_CODES.O)
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

                history = History(
                    slug = args.slug,
                    chapter_slug = args.chapter,
                    title = args.title,
                    chapter_title = it.title?.replace("${args.title} - Chapter ", ""),
                    poster = args.poster
                )

                setHistory(history)

                setPrevNextButton(Pair(!it.prev.isNullOrEmpty(), !it.next.isNullOrEmpty()))
            }
        )
    }

    private fun setPrevNextButton(state: Pair<Boolean, Boolean>) = with(binding) {
        if (state.first) imgPrev.visible() else imgPrev.gone()
        if (state.second) imgNext.visible() else imgNext.gone()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setHistory(history: History){
        historyViewModel.get(args.slug)

        historyViewModel.get.observerLiveData(viewLifecycleOwner,
            onEmpty = {},
            onLoading = {},
            onFailure = {_, _ ->
                historyViewModel.save(history)
            },
            onSuccess = {
                if (it.chapter_title?.toInt()!! <= history.chapter_title?.toInt()!!)
                    historyViewModel.update(history)
            }
        )
    }

    private fun onScrollListener() {
        with(binding) {
            rvReader.apply {
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (recyclerView.scrollState == RecyclerView.SCROLL_STATE_SETTLING) {
                            when {
                                dy > 0 -> {
                                    toolbar.gone()
                                    llBottom.gone()
                                }

                                dy < 0 -> {
                                    toolbar.visible()
                                    llBottom.visible()
                                }
                            }
                        }
                    }
                })
            }
        }
    }

}