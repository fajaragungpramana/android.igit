package com.github.fajaragungpramana.igit.core.data.remote.user.response

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(

    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("login")
    val login: String? = null,

    @SerializedName("bio")
    val bio: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("public_repos")
    val publicRepos: Int? = null,

    @SerializedName("followers")
    val followers: Int? = null,

    @SerializedName("following")
    val following: Int? = null

)