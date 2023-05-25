package com.wahidabd.shinigami.presentation.comic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.shinigami.databinding.FragmentComicReaderBinding
import com.wahidabd.shinigami.presentation.comic.adapter.ReaderAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicReaderFragment : BaseFragment<FragmentComicReaderBinding>() {

    private val viewModel: ComicViewModel by viewModel()
    private val args: ComicReaderFragmentArgs by navArgs()

    private val mAdapter by lazy { ReaderAdapter(requireContext()) }

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

        }
    }

    override fun initAction() {}

    override fun initProcess() {
        viewModel.reader(args.slug, args.chapter)
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
                mAdapter.setData = it.items
                debug { "${it.items}" }
            },
        )
    }

}