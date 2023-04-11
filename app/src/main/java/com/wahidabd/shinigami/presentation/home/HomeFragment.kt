package com.wahidabd.shinigami.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showLoadingState
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.shinigami.databinding.FragmentHomeBinding
import com.wahidabd.shinigami.presentation.home.adapter.LatestAdapter
import com.wahidabd.shinigami.presentation.home.adapter.RecommendedAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModel()

    private val recommendedAdapter by lazy {
        RecommendedAdapter(
            requireContext(),
            onItemClicked = {}
        )
    }

    private val latestAdapter by lazy {
        LatestAdapter(
            requireContext(),
            onItemClicked = {}
        )
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun initUI() {
        with(binding) {

            rvRecommended.apply {
                adapter = recommendedAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }

            rvLatest.apply {
                adapter = latestAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }

        }
    }

    override fun initAction() {
    }

    override fun initProcess() {
        viewModel.homeData()
    }

    override fun initObservers() {
        viewModel.homeData.observerLiveData(viewLifecycleOwner,
            onEmpty = {},
            onLoading = {
                binding.msvHome.showLoadingState()
            },
            onFailure = { _, message ->
                showToast(message.toString())
            },
            onSuccess = {
                binding.msvHome.showDefaultState()
                recommendedAdapter.setData = it.first
                latestAdapter.setData = it.second

                debug { "Second: ${it.second}" }
                debug { "Third: ${it.third}" }
                debug { "Fourth: ${it.fourth}" }

            }
        )
    }

}