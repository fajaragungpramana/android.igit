package com.github.fajaragungpramana.igit.common.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class AppFragment<VB : ViewBinding> : Fragment() {

    private lateinit var _viewBinding: VB
    val viewBinding: VB
        get() = _viewBinding

    protected abstract fun onViewBinding(container: ViewGroup?): VB

    protected abstract fun onViewCreated(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::_viewBinding.isInitialized) _viewBinding = onViewBinding(container)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onViewCreated(savedInstanceState)
    }

}