package com.github.fajaragungpramana.igit.module.repo

import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest

sealed class RepoEvent {

    data class ListRepo(val userRequest: UserRequest) : RepoEvent()

}