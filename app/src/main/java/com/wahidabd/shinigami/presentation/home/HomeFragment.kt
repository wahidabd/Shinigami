package com.wahidabd.shinigami.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showLoadingState
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.shinigami.R
import com.wahidabd.shinigami.databinding.FragmentHomeBinding
import com.wahidabd.shinigami.presentation.MainActivity
import com.wahidabd.shinigami.presentation.comic.ComicFragment
import com.wahidabd.shinigami.presentation.home.adapter.LatestAdapter
import com.wahidabd.shinigami.presentation.home.adapter.MirrorAdapter
import com.wahidabd.shinigami.presentation.home.adapter.RecommendedAdapter
import com.wahidabd.shinigami.presentation.home.adapter.TrendingAdapter
import com.wahidabd.shinigami.utils.Constant
import com.wahidabd.shinigami.utils.Constant.LATEST_COMIC
import com.wahidabd.shinigami.utils.Constant.MIRROR_COMIC
import com.wahidabd.shinigami.utils.Constant.TRENDING_COMIC
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

    private val trendingAdapter by lazy {
        TrendingAdapter(
            requireContext(),
            onItemClicked = {}
        )
    }

    private val mirrorAdapter by lazy {
        MirrorAdapter(
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

            rvPopular.apply {
                adapter = trendingAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }

            rvMirror.apply {
                adapter = mirrorAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }

        }
    }

    override fun initAction() {
        with(binding){

            latestContainer.onClick {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToComicFragment(LATEST_COMIC, Constant.orderLatest)
                )
            }

            trendingContainer.onClick {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToComicFragment(TRENDING_COMIC, Constant.orderTrending)
                )
            }

            mirrorContainer.onClick {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToComicFragment(MIRROR_COMIC, Constant.orderMirror)
                )
            }

        }
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
                trendingAdapter.setData = it.third
                mirrorAdapter.setData = it.fourth
            }
        )
    }

}