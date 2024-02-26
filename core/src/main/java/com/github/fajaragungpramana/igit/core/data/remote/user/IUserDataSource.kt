package com.github.fajaragungpramana.igit.core.data.remote.user

import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserDataResponse
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserDetailResponse
import com.github.fajaragungpramana.igit.core.data.remote.user.response.RepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface IUserDataSource {

    @GET("search/users")
    suspend fun searchUser(@QueryMap userRequest: UserRequest): Response<UserDataResponse>

    @GET("users/{username}")
    suspend fun userDetail(@Path("username") username: String): Response<UserDetailResponse>

    @GET("users/{username}/followers")
    suspend fun followers(
        @Path("username") username: String,
        @QueryMap userRequest: UserRequest
    ): Response<List<UserDetailResponse>>

    @GET("users/{username}/following")
    suspend fun following(
        @Path("username") username: String,
        @QueryMap userRequest: UserRequest
    ): Response<List<UserDetailResponse>>

    @GET("users/{username}/repos")
    suspend fun repos(
        @Path("repos") repo: String,
        @QueryMap userRequest: UserRequest
    ): Response<List<RepoResponse>>

}