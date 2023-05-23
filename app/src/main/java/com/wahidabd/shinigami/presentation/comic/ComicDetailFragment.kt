package com.wahidabd.shinigami.presentation.comic

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showLoadingState
import com.wahidabd.library.utils.exts.getCompatColor
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.shinigami.R
import com.wahidabd.shinigami.databinding.FragmentComicDetailBinding
import com.wahidabd.shinigami.domain.comic.model.Chapter
import com.wahidabd.shinigami.domain.comic.model.ComicDetail
import com.wahidabd.shinigami.presentation.comic.adapter.ChapterAdapter
import com.wahidabd.shinigami.presentation.comic.adapter.GenreAdapter
import com.wahidabd.shinigami.utils.customview.setResizableText
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicDetailFragment : BaseFragment<FragmentComicDetailBinding>() {

    private val args: ComicDetailFragmentArgs by navArgs()
    private val viewModel: ComicViewModel by viewModel()

    private val adapter by lazy {
        ChapterAdapter(
            requireContext(),
            onItemClicked = {}
        )
    }

    private val genreAdapter by lazy {
        GenreAdapter(requireContext())
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentComicDetailBinding {
        return FragmentComicDetailBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        binding.apply {
            rvChapter.apply {
                adapter = this@ComicDetailFragment.adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            rvGenre.apply {
                adapter = genreAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }

            nestedScroll.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                if (scrollY >= 90) {
                    toolbar.setBackgroundColorResource(R.color.darkGray)
                    toolbar.enableTitle(true)
                } else {
                    toolbar.setBackgroundColorResource()
                    toolbar.enableTitle(false)
                }
            }
        }

    }

    override fun initAction() {
        with(binding) {
            toolbar.setEnableBack { findNavController().navigateUp() }
        }
    }

    override fun initProcess() {
        viewModel.detail(args.slug)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun initObservers() {
        viewModel.detail.observerLiveData(viewLifecycleOwner,
            onEmpty = {},
            onLoading = {
                binding.msvDetail.showLoadingState()
            },
            onFailure = { _, m ->
                showToast(m.toString())
            },
            onSuccess = {
                binding.msvDetail.showDefaultState()
                setupHeader(it)
                adapter.setData = it.chapters as List<Chapter>
                genreAdapter.setData = it.genres as List<String>
                binding.apply {
                    tvTotalChapters.text = getString(R.string.format_chapters, it.chapters.size.toString())
                    tvSynopsis.setResizableText(it.synopsis.toString(), 4, true)
                    toolbar.setTitle(it.title.toString())
                }
            }
        )
    }


    private fun setupHeader(data: ComicDetail) {
        data.apply {
            binding.header.setInformation(
                title = title.toString(),
                author = author.toString(),
                status = status.toString(),
                genres = genres,
                poster = imagePoster.toString(),
                banner = imageBanner.toString()
            )
        }
    }
}
