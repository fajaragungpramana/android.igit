package com.github.fajaragungpramana.igit.core.domain.user.model

import androidx.recyclerview.widget.DiffUtil
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.UserEntity
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserDetailResponse

data class User(
    var avatar: String? = null,
    var username: String? = null,
    var fullName: String? = null,
    var bio: String? = null,
    var email: String? = null,
    var totalRepository: Int? = null,
    var totalFollower: Int? = null,
    var totalFollowing: Int? = null
) {

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<User>() {

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.username == newItem.username

            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem

        }

        fun mapFromResponseToList(userDetail: UserDetailResponse?) = User(
            avatar = userDetail?.avatarUrl,
            username = userDetail?.login,
            fullName = userDetail?.name,
            bio = userDetail?.bio,
            email = userDetail?.email,
            totalRepository = userDetail?.publicRepos,
            totalFollower = userDetail?.followers,
            totalFollowing = userDetail?.following
        )

        fun mapFromEntityToList(listUserEntity: List<UserEntity>): List<User> {
            val list = arrayListOf<User>()
            listUserEntity.forEach {
                list.add(
                    User(
                        avatar = it.avatar,
                        username = it.username,
                        fullName = it.fullName
                    )
                )
            }

            return list
        }

    }

}