package com.github.fajaragungpramana.igit.core.domain.user.model

import androidx.recyclerview.widget.DiffUtil
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserDetailResponse

data class User(
    var avatar: String? = null,
    var username: String? = null,
    var fullName: String? = null
) {

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<User>() {

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.username == newItem.username

            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem

        }

        fun mapToObject(userDetail: UserDetailResponse?) = User(
            avatar = userDetail?.avatarUrl,
            username = userDetail?.login,
            fullName = userDetail?.name
        )

    }

}