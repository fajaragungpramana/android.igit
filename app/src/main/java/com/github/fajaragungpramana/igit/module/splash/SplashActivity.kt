package com.github.fajaragungpramana.igit.module.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.github.fajaragungpramana.igit.common.app.AppActivity
import com.github.fajaragungpramana.igit.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppActivity<ActivitySplashBinding>() {

    private var keep = true

    override fun onViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreated(savedInstanceState: Bundle?) {
        installSplashScreen().let {
            it.setKeepOnScreenCondition { keep }
            it.setOnExitAnimationListener {

            }
        }

        Handler(mainLooper).postDelayed({ keep = false }, DELAY)
    }

    private companion object {
        const val DELAY = 3000L
    }

}