package com.github.fajaragungpramana.igit.common.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.github.fajaragungpramana.igit.common.contract.AppState

abstract class AppActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _viewBinding: VB? = null
    protected val viewBinding: VB
        get() = _viewBinding ?: throw NullPointerException("View is not inflated")

    protected abstract fun onViewBinding(): VB

    protected abstract fun onCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (_viewBinding == null) _viewBinding = onViewBinding()
        setContentView(viewBinding.root)

        if (this is AppState) onStateObserver()

        onCreated(savedInstanceState)
    }

    override fun onDestroy() {
        _viewBinding = null

        super.onDestroy()
    }

}