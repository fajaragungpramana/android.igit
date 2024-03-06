package com.github.fajaragungpramana.igit.module.main

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.github.fajaragungpramana.igit.R
import com.github.fajaragungpramana.igit.common.app.AppActivity
import com.github.fajaragungpramana.igit.core.data.local.cache.CacheManager
import com.github.fajaragungpramana.igit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@AndroidEntryPoint
class MainActivity : AppActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    private val navigationController by lazy { supportFragmentManager.findFragmentById(R.id.fcv_main_container) as NavHostFragment }

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

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMainEvent(event: MainEvent) {
        when (event) {
            is MainEvent.Theme -> {
                viewModel.setCache(CacheManager.IS_DARK_THEME, event.isDark)

                lifecycleScope.launch {
                    AppCompatDelegate.setDefaultNightMode(
                        if (event.isDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                    )
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        navigationController.navController.addOnDestinationChangedListener { _, destination, _ ->
            menu?.findItem(R.id.item_favorite)?.isVisible = destination.id == R.id.search_fragment
            menu?.findItem(R.id.item_add_favorite)?.isVisible =
                destination.id == R.id.detail_fragment
            menu?.findItem(R.id.item_setting)?.isVisible = destination.id == R.id.search_fragment
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    private fun initView() {
        setSupportActionBar(viewBinding.mtlMain)

        viewBinding.apply {
            mtlMain.setNavigationOnClickListener { navigationController.navController.navigateUp() }
        }
        navigationController.navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(destination.id != R.id.search_fragment)
        }
    }

    private companion object {
        const val DELAY = 3000L
    }

}