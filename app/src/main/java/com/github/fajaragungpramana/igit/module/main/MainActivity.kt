package com.github.fajaragungpramana.igit.module.main

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.github.fajaragungpramana.igit.common.app.AppActivity
import com.github.fajaragungpramana.igit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class MainActivity : AppActivity<ActivityMainBinding>() {

    private var keep = true

    override fun onViewBinding(): ActivityMainBinding {
        installSplashScreen().let {
            it.setKeepOnScreenCondition { keep }
            it.setOnExitAnimationListener { viewProvider ->

                val slideDown = ObjectAnimator.ofFloat(
                    viewProvider.view,
                    View.TRANSLATION_Y,
                    0f,
                    +viewProvider.view.height.toFloat()
                )
                slideDown.interpolator = AnticipateInterpolator()
                slideDown.duration = 200L

                slideDown.doOnEnd { viewProvider.remove() }

                slideDown.start()
            }
        }

        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        Handler(mainLooper).postDelayed({ keep = false }, DELAY)
    }

    private companion object {
        const val DELAY = 3000L
    }

}