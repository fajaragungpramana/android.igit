package com.github.fajaragungpramana.igit.module.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.map
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.domain.user.UserInteractor
import com.github.fajaragungpramana.igit.core.domain.user.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestDispatcher
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
class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var userInteractor: UserInteractor

    private lateinit var viewModel: SearchViewModel

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = SearchViewModel(userInteractor)
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
    fun `search user success should set state to UserData`() = runTest {
        val expected = PagingData.from(
            listOf(
                User(
                    avatar = "https://avatars.githubusercontent.com/u/47925662?s=400&u=e15d06caa4f49c9427a080c02f03b86f250f8a90&v=4",
                    username = "fajaragungpramana",
                    fullName = "Fajar Agung Pramana"
                )
            )
        )
        val request = UserRequest(q = "fajaragungpramana", page = 1, perPage = 12)
        `when`(userInteractor.getListUser(request)).thenReturn(channelFlow { send(expected) })

        viewModel.setEvent(SearchEvent.SearchUser(request))

        val result = viewModel.state.first()
        Assert.assertTrue(result is SearchState.UserData)
        Assert.assertEquals(expected, (result as SearchState.UserData).pagingData)
    }

}