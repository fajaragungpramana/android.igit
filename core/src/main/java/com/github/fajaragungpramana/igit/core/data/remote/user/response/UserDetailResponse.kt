package com.github.fajaragungpramana.igit.core.data.remote.user.response

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(

    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("login")
    val login: String? = null

)