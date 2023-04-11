package com.wahidabd.shinigami.presentation

import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.shinigami.R
import com.wahidabd.shinigami.databinding.ActivityMainBinding
import com.wahidabd.shinigami.presentation.home.HomeFragment
import com.wahidabd.shinigami.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initUI() {
    }

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}

}