package com.github.fajaragungpramana.igit.common.app

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding

abstract class AppPagingAdapter<VB : ViewBinding, T: Any, VH : AppRecyclerViewHolder<T>>(
    diffUtil: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, VH>(diffUtil) {

    private lateinit var _viewBinding: VB
    protected val viewBinding: VB
        get() = _viewBinding

    protected abstract fun onViewBinding(viewGroup: ViewGroup): VB

    protected abstract fun onViewHolder(itemView: View): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        _viewBinding = onViewBinding(parent)
        return onViewHolder(viewBinding.root)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        try {
            val item = getItem(position)
            if (item != null) holder.bindItem(item, position)
        } catch (e: ArrayIndexOutOfBoundsException) {
            e.printStackTrace()
        }
    }

    override fun getItemViewType(position: Int) = position

}