package com.github.fajaragungpramana.igit.module.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.fajaragungpramana.igit.common.app.AppPagingAdapter
import com.github.fajaragungpramana.igit.common.app.AppRecyclerViewHolder
import com.github.fajaragungpramana.igit.core.domain.user.model.Repo
import com.github.fajaragungpramana.igit.databinding.ItemRepoBinding

class RepoAdapter : AppPagingAdapter<ItemRepoBinding, Repo, RepoAdapter.ViewHolder>(Repo.diffUtil) {

    override fun onViewBinding(viewGroup: ViewGroup): ItemRepoBinding =
        ItemRepoBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

    override fun onViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    inner class ViewHolder(itemView: View) : AppRecyclerViewHolder<Repo>(itemView) {

        override fun bindItem(item: Repo, position: Int) {
            viewBinding.apply {
                mtvRepoName.text = item.name
                mtvRepoDescription.text = item.description
                mtvRepoStar.text = item.starCount.toString()
            }
        }

    }

}