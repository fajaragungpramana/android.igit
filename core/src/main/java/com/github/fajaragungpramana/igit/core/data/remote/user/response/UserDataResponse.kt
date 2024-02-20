package com.github.fajaragungpramana.igit.core.data.remote.user.response

import com.google.gson.annotations.SerializedName

data class UserDataResponse(

    @SerializedName("items")
    val listItem: List<UserResponse>? = null

)