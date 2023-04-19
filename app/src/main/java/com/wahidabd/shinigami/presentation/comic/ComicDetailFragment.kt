package com.wahidabd.shinigami.presentation.comic

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.shinigami.databinding.FragmentComicDetailBinding

class ComicDetailFragment : BaseFragment<FragmentComicDetailBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentComicDetailBinding {
        return FragmentComicDetailBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}
}
