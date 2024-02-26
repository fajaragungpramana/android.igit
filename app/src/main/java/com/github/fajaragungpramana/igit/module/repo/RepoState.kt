package com.github.fajaragungpramana.igit.module.repo

import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.domain.user.model.Repo

sealed class RepoState {

    data class RepoData(val pagingData: PagingData<Repo>): RepoState()

}