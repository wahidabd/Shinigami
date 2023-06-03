package com.wahidabd.shinigami.presentation.history

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.shinigami.databinding.FragmentHistoryBinding
import com.wahidabd.shinigami.domain.history.model.History
import com.wahidabd.shinigami.presentation.history.adapter.HistoryAdapter
import com.wahidabd.shinigami.utils.showMaterialAlert
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val viewModel: HistoryViewModel by viewModel()
    private val mAdapter by lazy {
        HistoryAdapter(
            requireContext(),
            onItemClicked = {},
            onItemRemoved = {
                alert(it)
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
        with(binding) {
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
            onEmpty = {
                binding.msv.viewState = MultiStateView.ViewState.EMPTY
            },
            onLoading = {},
            onFailure = { _, message ->
                showToast(message.toString())
            },
            onSuccess = {
                binding.msv.showDefaultState()
                mAdapter.setData = it
            }
        )
    }

    private fun alert(data: History) {
        showMaterialAlert(
            requireContext(),
            "Remove",
            "Remove ${data.title} from history?",
            positiveButton = {
                viewModel.remove(data)
                it.dismiss()
                viewModel.list()
            },
            negativeButton = {
                it.dismiss()
            }
        ).show()
    }

}