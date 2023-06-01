package com.wahidabd.shinigami.presentation.onboarding

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.shinigami.databinding.ActivitySplashBinding
import com.wahidabd.shinigami.presentation.MainActivity
import com.wahidabd.shinigami.utils.pref.PreferenceManager
import org.koin.android.ext.android.inject

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val pref: PreferenceManager by inject()

    override fun getViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    override fun initAction() {}

    override fun initProcess() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (pref.getLogin()) {
                MainActivity.start(this)
                finish()
            } else {
                LoginActivity.start(this)
                finish()
            }
        }, 1500)
    }

    override fun initObservers() {}

}