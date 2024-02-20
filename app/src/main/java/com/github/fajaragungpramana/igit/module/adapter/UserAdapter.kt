package com.github.fajaragungpramana.igit.module.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.transform.CircleCropTransformation
import com.github.fajaragungpramana.igit.common.app.AppPagingAdapter
import com.github.fajaragungpramana.igit.common.app.AppRecyclerViewHolder
import com.github.fajaragungpramana.igit.core.domain.user.model.User
import com.github.fajaragungpramana.igit.databinding.ItemUserBinding

class UserAdapter : AppPagingAdapter<ItemUserBinding, User, UserAdapter.ViewHolder>(User.diffUtil) {

    override fun onViewBinding(viewGroup: ViewGroup): ItemUserBinding =
        ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

    override fun onViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    inner class ViewHolder(itemView: View) : AppRecyclerViewHolder<User>(itemView) {

        override fun bindItem(item: User, position: Int) {
            viewBinding.aivUserAvatar.load(item.avatar) {
                transformations(CircleCropTransformation())
            }
            viewBinding.mtvUsername.text = item.username
            viewBinding.mtvUserFullName.text = item.fullName
        }

    }

}