package com.github.fajaragungpramana.igit.core.domain.user.model

import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserDetailResponse

data class User(
    var avatar: String? = null,
    var username: String? = null,
    var fullName: String? = null
) {

    companion object {

        fun mapToObject(userDetail: UserDetailResponse?) = User(
            avatar = userDetail?.avatarUrl,
            username = userDetail?.login,
            fullName = userDetail?.name
        )

    }

}