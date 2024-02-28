package com.github.fajaragungpramana.igit.module.follow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.data.remote.user.UserPagingSource
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.domain.user.UserUseCase
import com.github.fajaragungpramana.igit.core.domain.user.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FollowViewModelTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var userUseCase: UserUseCase

    private lateinit var viewModel: FollowViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = FollowViewModel(userUseCase)
    }

    @Before
    fun setDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getListFollow success should set state to UserData`() = runTest {
        val expected = PagingData.from(
            listOf(
                User(
                    avatar = "https://avatars.githubusercontent.com/u/47925662?s=400&u=e15d06caa4f49c9427a080c02f03b86f250f8a90&v=4",
                    username = "fajaragungpramana"
                )
            )
        )
        val userRequest = UserRequest(
            username = "fajaragungpramana",
            page = 1,
            perPage = 12,
            type = UserPagingSource.Type.FOLLOWING
        )
        `when`(userUseCase.getListUser(userRequest)).thenReturn(channelFlow { send(expected) })

        viewModel.setEvent(FollowEvent.ListFollow(userRequest))

        val result = viewModel.state.first()
        Assert.assertTrue(result is FollowState.UserData)
        Assert.assertEquals(expected, (result as FollowState.UserData).pagingData)
    }

}