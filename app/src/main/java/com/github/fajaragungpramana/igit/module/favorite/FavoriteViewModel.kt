package com.github.fajaragungpramana.igit.module.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.domain.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {

    private val _state by lazy { Channel<FavoriteState>() }
    val state: Flow<FavoriteState>
        get() = _state.receiveAsFlow()

    fun setEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.ListUser -> getListFavoriteUser()
        }
    }

    private fun getListFavoriteUser(): Job = viewModelScope.launch {
        userUseCase.getListFavoriteUser().collectLatest {
            when (it) {
                is AppResult.Success ->
                    _state.send(FavoriteState.UserData(it.data ?: PagingData.empty()))

                is AppResult.Error -> _state.send(FavoriteState.MessageData(it.message))
            }
        }
    }

}