package com.wahidabd.shinigami.presentation.more

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.shinigami.databinding.FragmentMoreBinding

class MoreFragment : BaseFragment<FragmentMoreBinding>() {
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentMoreBinding {
        return FragmentMoreBinding.inflate(layoutInflater)
    }

    override fun initUI() {    }

    override fun initAction() {}

    override fun initObservers() {}

    override fun initProcess() {}

}