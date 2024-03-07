package com.github.fajaragungpramana.igit.common.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.github.fajaragungpramana.igit.common.contract.AppState

abstract class AppFragment<VB : ViewBinding> : Fragment() {

    private var _viewBinding: VB? = null
    protected val viewBinding: VB
        get() = _viewBinding ?: throw NullPointerException("View is not inflated.")

    protected abstract fun onViewBinding(container: ViewGroup?): VB

    protected abstract fun onViewCreated(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_viewBinding == null) _viewBinding = onViewBinding(container)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onViewCreated(savedInstanceState)

        if (this is AppState) onStateObserver()
    }

    override fun onDetach() {
        _viewBinding = null

        super.onDetach()
    }

}