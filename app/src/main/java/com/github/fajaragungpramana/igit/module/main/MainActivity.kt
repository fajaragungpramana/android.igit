package com.github.fajaragungpramana.igit.module.main

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.github.fajaragungpramana.igit.R
import com.github.fajaragungpramana.igit.common.app.AppActivity
import com.github.fajaragungpramana.igit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

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
        initView()

        Handler(mainLooper).postDelayed({ keep = false }, DELAY)
    }

    private fun initView() {
        setSupportActionBar(viewBinding.mtlMain)

        val navController = supportFragmentManager.findFragmentById(R.id.fcv_main_container) as NavHostFragment
        viewBinding.apply {
            mtlMain.setNavigationOnClickListener { navController.navController.navigateUp() }
        }
        navController.navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(destination.id != R.id.searchFragment)
        }
    }

    private companion object {
        const val DELAY = 3000L
    }

}