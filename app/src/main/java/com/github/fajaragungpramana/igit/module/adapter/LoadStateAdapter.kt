package com.github.fajaragungpramana.igit.module.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.github.fajaragungpramana.igit.common.app.AppPagingStateAdapter
import com.github.fajaragungpramana.igit.common.app.AppRecyclerViewHolder
import com.github.fajaragungpramana.igit.databinding.ItemLoadStateBinding

class LoadStateAdapter : AppPagingStateAdapter<ItemLoadStateBinding, LoadStateAdapter.ViewHolder>() {

    override fun onViewBinding(viewGroup: ViewGroup): ItemLoadStateBinding =
        ItemLoadStateBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

    override fun onViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    inner class ViewHolder(itemView: View) : AppRecyclerViewHolder<LoadState>(itemView) {

        override fun bindItem(item: LoadState, position: Int) {
            viewBinding.lavHorizontalLoading.apply {
                val isLoading = item is LoadState.Loading

                isVisible = isLoading
                if (isLoading) playAnimation() else pauseAnimation()
            }
        }

    }

}