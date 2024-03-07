package com.github.fajaragungpramana.igit.module.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.igit.constant.EspressoIdlingResource
import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.UserEntity
import com.github.fajaragungpramana.igit.core.domain.user.UserUseCase
import com.github.fajaragungpramana.igit.core.domain.user.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {

    private val _state by lazy { Channel<DetailState>() }
    val state: Flow<DetailState>
        get() = _state.receiveAsFlow()

    fun setEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.UserDetail -> getUser(event.username)
            is DetailEvent.UserIsFavorite -> isUserFavorite(event.username)
            is DetailEvent.UserFavorite -> saveUserFavorite(event.user)
        }
    }

    private fun getUser(username: String): Job = viewModelScope.launch {
        EspressoIdlingResource.increment()

        _state.send(DetailState.UserLoading(true))
        userUseCase.getUser(username).collectLatest {
            if (!EspressoIdlingResource.idlingResource.isIdleNow)
                EspressoIdlingResource.decrement()

            when (it) {
                is AppResult.Success -> {
                    _state.send(DetailState.UserData(it.data ?: User()))
                    _state.send(DetailState.UserLoading(false))
                }

                is AppResult.Error -> _state.send(DetailState.MessageData(it.message))
            }
        }
    }

    private fun isUserFavorite(username: String): Job = viewModelScope.launch {
        userUseCase.getFavoriteUser(username).collectLatest {
            _state.send(DetailState.UserFavorite(!it.username.isNullOrEmpty()))
        }
    }

    private fun saveUserFavorite(user: User): Job = viewModelScope.launch {
        userUseCase.getFavoriteUser(user.username.orEmpty()).collectLatest {
            if (it.username.isNullOrEmpty())
                userUseCase.saveFavoriteUser(
                    UserEntity(
                        avatar = user.avatar,
                        username = user.username,
                        fullName = user.fullName
                    )
                ).collectLatest { value -> _state.send(DetailState.UserFavorite(value)) }
            else
                userUseCase.deleteFavoriteUser(user.username.orEmpty())
                    .collectLatest { value -> _state.send(DetailState.UserFavorite(!value)) }
        }
    }

}