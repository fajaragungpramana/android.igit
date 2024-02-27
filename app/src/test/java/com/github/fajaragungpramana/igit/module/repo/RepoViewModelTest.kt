package com.github.fajaragungpramana.igit.module.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.domain.user.UserUseCase
import com.github.fajaragungpramana.igit.core.domain.user.model.Repo
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
class RepoViewModelTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var userUseCase: UserUseCase

    private lateinit var viewModel: RepoViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = RepoViewModel(userUseCase)
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
    fun `getListRepo success should set state to RepoData`() = runTest {
        val expected = PagingData.from(
            listOf(
                Repo(
                    id = 1,
                    name = "android.ex",
                    description = "App for management money expenses",
                    starCount = 1
                )
            )
        )
        val request = UserRequest(username = "fajaragungpramana", page = 1, perPage = 12)
        `when`(userUseCase.getListRepo(request)).thenReturn(channelFlow { send(expected) })

        viewModel.setEvent(RepoEvent.ListRepo(request))

        val result = viewModel.state.first()

        Assert.assertTrue(result is RepoState.RepoData)
        Assert.assertEquals(expected, (result as RepoState.RepoData).pagingData)
    }

}