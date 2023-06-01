package com.wahidabd.shinigami.presentation

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.shinigami.R
import com.wahidabd.shinigami.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initUI() {
        with(binding) {
            val navHost =
                supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
            val navController = navHost.navController
//            bottomNav.setupWithNavController(navController)

            bottomNav.setOnItemSelectedListener { item ->
                if (item.itemId != bottomNav.selectedItemId) {
                    NavigationUI.onNavDestinationSelected(item, navController)
                }
                true
            }

            navController.addOnDestinationChangedListener { _, dest, _ ->
                when (dest.id) {
                    R.id.homeFragment,
                    R.id.favoriteFragment,
                    R.id.historyFragment,
                    R.id.libraryFragment,
                    R.id.moreFragment -> bottomNav.visible()

                    else -> bottomNav.gone()
                }
            }
        }
    }

    override fun initAction() {
    }

    override fun initProcess() {}

    override fun initObservers() {}

}