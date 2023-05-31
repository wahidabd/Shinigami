package com.wahidabd.shinigami.presentation.history

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showEmptyState
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.shinigami.databinding.FragmentHistoryBinding
import com.wahidabd.shinigami.presentation.history.adapter.HistoryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val viewModel: HistoryViewModel by viewModel()
    private val mAdapter by lazy {
        HistoryAdapter(
            requireContext(),
            onItemClicked = {},
            onItemRemoved = {
                viewModel.remove(it)
            }
        )
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentHistoryBinding {
        return FragmentHistoryBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        with(binding){
            rvHistory.apply {
                adapter = mAdapter
            }
        }
    }

    override fun initAction() {}

    override fun initProcess() {
        viewModel.list()
    }

    override fun initObservers() {
        viewModel.list.observerLiveData(viewLifecycleOwner,
            onEmpty = {},
            onLoading = {
                binding.msv.showEmptyState()
            },
            onFailure = { _, message ->
                showToast(message.toString())
            },
            onSuccess = {
                binding.msv.showDefaultState()
                mAdapter.setData = it
            }
        )
    }

}