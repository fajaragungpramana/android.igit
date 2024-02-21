package com.github.fajaragungpramana.igit.common.app

import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.viewbinding.ViewBinding

abstract class AppPagingStateAdapter<VB : ViewBinding, VH : AppRecyclerViewHolder<LoadState>> :
    LoadStateAdapter<VH>() {

    private lateinit var _viewBinding: VB
    protected val viewBinding: VB
        get() = _viewBinding

    protected abstract fun onViewBinding(viewGroup: ViewGroup): VB

    protected abstract fun onViewHolder(itemView: View): VH

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): VH {
        _viewBinding = onViewBinding(parent)
        return onViewHolder(viewBinding.root)
    }

    override fun onBindViewHolder(holder: VH, loadState: LoadState) {
        holder.bindItem(loadState, 0)
    }

}