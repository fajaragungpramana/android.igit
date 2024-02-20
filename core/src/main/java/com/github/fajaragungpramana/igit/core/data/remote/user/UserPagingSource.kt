package com.github.fajaragungpramana.igit.core.data.remote.user

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserDetailResponse
import javax.inject.Inject

class UserPagingSource @Inject constructor(
    private val userDataSource: IUserDataSource,
    private val userRequest: UserRequest
) : PagingSource<Int, UserDetailResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDetailResponse> = try {
        val listUserDetailResponse = arrayListOf<UserDetailResponse>()

        val page = params.key ?: 1

        val listUserResponse = userDataSource.searchUser(userRequest.copy(page = page))
        if (listUserResponse.isSuccessful) {
            val listUser = listUserResponse.body()?.listItem
            if (!listUser.isNullOrEmpty()) listUser.forEach {
                val username = it.login.orEmpty()

                if (username.isNotEmpty()) {
                    val userDetailResponse = userDataSource.userDetail(username)
                    val userDetail = userDetailResponse.body()
                    if (userDetail != null) listUserDetailResponse.add(userDetail)
                }

            }
        }

        LoadResult.Page(
            data = listUserDetailResponse,
            prevKey = if (page == 1) null else page,
            nextKey = if (listUserDetailResponse.isEmpty()) null else page + 1
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, UserDetailResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

}