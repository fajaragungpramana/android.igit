package com.github.fajaragungpramana.igit.core.data.remote.user

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.data.remote.user.response.RepoResponse
import javax.inject.Inject

class RepoPagingSource @Inject constructor(
    private val userDataSource: IUserDataSource,
    private val userRequest: UserRequest
) : PagingSource<Int, RepoResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoResponse> = try {
        val page = params.key ?: 1

        val listRepoResponse = userDataSource.repos(
            userRequest.username.orEmpty(),
            userRequest.copy(page = page)
        ).body().orEmpty()
        LoadResult.Page(
            data = listRepoResponse,
            prevKey = if (page == 1) null else page,
            nextKey = if (listRepoResponse.isEmpty()) null else page + 1
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, RepoResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

}