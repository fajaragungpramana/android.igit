package com.github.fajaragungpramana.igit.common.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.github.fajaragungpramana.igit.common.contract.AppState

abstract class AppActivity<VB : ViewBinding> : AppCompatActivity() {

    private lateinit var _viewBinding: VB
    protected val viewBinding: VB
        get() = _viewBinding

    protected abstract fun onViewBinding(): VB

    protected abstract fun onCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!::_viewBinding.isInitialized) _viewBinding = onViewBinding()
        setContentView(viewBinding.root)

        if (this is AppState) onStateObserver()

        onCreated(savedInstanceState)
    }

}