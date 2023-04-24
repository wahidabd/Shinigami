package com.wahidabd.shinigami.presentation.comic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.shinigami.databinding.FragmentComicDetailBinding
import com.wahidabd.shinigami.domain.comic.model.ComicDetail
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicDetailFragment : BaseFragment<FragmentComicDetailBinding>() {

    private val args: ComicDetailFragmentArgs by navArgs()
    private val viewModel: ComicViewModel by viewModel()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentComicDetailBinding {
        return FragmentComicDetailBinding.inflate(layoutInflater)
    }

    override fun initUI() {
    }

    override fun initAction() {
        with(binding){
            header.setLeftButton { findNavController().navigateUp() }
        }
    }

    override fun initProcess() {
        viewModel.detail(args.slug)
    }

    override fun initObservers() {
        viewModel.detail.observerLiveData(viewLifecycleOwner,
            onEmpty = {},
            onLoading = {},
            onFailure = { _, m ->
                showToast(m.toString())
            },
            onSuccess = {
                setupHeader(it)
            }
        )
    }


    private fun setupHeader(data: ComicDetail){
        data.apply {
            binding.header.setInformation(
                title = title.toString(),
                author = author.toString(),
                status = status.toString(),
                poster = imagePoster.toString(),
                banner = imageBanner.toString()
            )
        }
    }
}
