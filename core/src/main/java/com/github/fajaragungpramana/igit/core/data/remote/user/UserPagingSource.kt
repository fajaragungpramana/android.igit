package com.github.fajaragungpramana.igit.core.data.remote.user

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserResponse
import javax.inject.Inject

class UserPagingSource @Inject constructor(
    private val userDataSource: IUserDataSource,
    private val userRequest: UserRequest
) : PagingSource<Int, UserResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> = try {
        val page = params.key ?: 1
        val response = userDataSource.searchUser(userRequest.copy(page = page))
        LoadResult.Page(
            data = response.body()?.listItem ?: listOf(),
            prevKey = null,
            nextKey = page + 1
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, UserResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

}