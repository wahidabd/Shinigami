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
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showLoadingState
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.shinigami.R
import com.wahidabd.shinigami.databinding.FragmentComicDetailBinding
import com.wahidabd.shinigami.domain.comic.model.Chapter
import com.wahidabd.shinigami.domain.comic.model.ComicDetail
import com.wahidabd.shinigami.domain.favorite.model.Favorite
import com.wahidabd.shinigami.presentation.comic.adapter.ChapterAdapter
import com.wahidabd.shinigami.presentation.comic.adapter.GenreAdapter
import com.wahidabd.shinigami.presentation.favorite.FavoriteViewModel
import com.wahidabd.shinigami.utils.customview.setResizableText
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicDetailFragment : BaseFragment<FragmentComicDetailBinding>() {

    private val args: ComicDetailFragmentArgs by navArgs()
    private val viewModel: ComicViewModel by viewModel()
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var favorite = Favorite()
    private var isFavorite = false

    private var title = emptyString()

    private val adapter by lazy {
        ChapterAdapter(
            requireContext(),
            onItemClicked = {
                navToReader(it, title)
            }
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
                    toolbarContainer.setBackgroundColorResource(R.color.darkBlue)
                    toolbarContainer.enableTitle(true)
                } else {
                    toolbarContainer.setBackgroundColorResource()
                    toolbarContainer.enableTitle(false)
                }
            }
        }

    }

    override fun initAction() {
        with(binding) {
            toolbarContainer.setEnableBack { findNavController().navigateUp() }
            toolbarContainer.setImageMainEnable { setFavorite() }
        }
    }

    override fun initProcess() {
        viewModel.detail(args.slug)
        favoriteViewModel.get(args.slug)
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
                title = it.title.toString()
                setupHeader(it)
                adapter.setData = it.chapters as List<Chapter>
                genreAdapter.setData = it.genres as List<String>
                binding.apply {
                    tvTotalChapters.text =
                        getString(R.string.format_chapters, it.chapters.size.toString())
                    tvSynopsis.setResizableText(it.synopsis.toString(), 4, true)
                    toolbarContainer.setTitle(it.title.toString())
                }

                favorite = Favorite(slug = it.slug, title = it.title, poster = it.imagePoster)
            }
        )

        favoriteViewModel.add.observerLiveData(viewLifecycleOwner,
            onEmpty = {},
            onFailure = {_, m ->
                showToast(m.toString())
            },
            onLoading = {},
            onSuccess = {}
        )

        favoriteViewModel.get.observerLiveData(viewLifecycleOwner,
            onEmpty = {},
            onLoading = {},
            onFailure = {_,_ ->
                isFavorite = false
                checkFavorite()
            },
            onSuccess = {
                isFavorite = true
                checkFavorite()
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

    private fun setFavorite() {
        if (!isFavorite) favoriteViewModel.add(favorite)
        else favoriteViewModel.remove(favorite)

        isFavorite = !isFavorite
        checkFavorite()
    }

    private fun checkFavorite() = with(binding.toolbarContainer) {
        if (isFavorite) setIconMain(R.drawable.ic_favorite_fill)
        else setIconMain(R.drawable.ic_favorite_outline)
    }

    private fun navToReader(ch: String, title: String) {
        findNavController().navigate(
            ComicDetailFragmentDirections.actionComicDetailFragmentToComicReaderFragment(ch, title)
        )
    }
}
