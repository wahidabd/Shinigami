package com.wahidabd.shinigami.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showEmptyState
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.shinigami.databinding.FragmentFavoriteBinding
import com.wahidabd.shinigami.presentation.favorite.adapter.FavoriteAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private val viewModel: FavoriteViewModel by viewModel()

    private val mAdapter by lazy {
        FavoriteAdapter(
            requireContext(),
            onItemClicked = {
                findNavController().navigate(
                    FavoriteFragmentDirections.actionFavoriteFragmentToComicDetailFragment(it)
                )
            }
        )
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(layoutInflater)
    }

    override fun initUI(){
        binding.rvFavorite.apply {
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun initAction() {}

    override fun initProcess() {
        viewModel.getFavorites()
    }

    override fun initObservers() {
        viewModel.getFavorites.observerLiveData(viewLifecycleOwner,
            onLoading = {},
            onFailure = {_, m ->
                showToast(m.toString())
            },
            onEmpty = {
                binding.msv.showEmptyState()
            },
            onSuccess = {
                binding.msv.showDefaultState()
                mAdapter.setData = it
            }
        )
    }

}