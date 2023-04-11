package com.wahidabd.shinigami.presentation.library

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.shinigami.databinding.FragmentLibraryBinding


class LibraryFragment : BaseFragment<FragmentLibraryBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentLibraryBinding {
        return FragmentLibraryBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {}

    override fun initObservers() {}

    override fun initProcess() {}


}