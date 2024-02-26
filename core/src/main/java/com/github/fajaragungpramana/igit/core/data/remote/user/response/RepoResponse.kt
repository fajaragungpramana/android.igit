package com.github.fajaragungpramana.igit.core.data.remote.user.response

import com.google.gson.annotations.SerializedName

data class RepoResponse(

    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("stargazers_count")
    val stargazersCount: Int? = null

)