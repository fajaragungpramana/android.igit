package com.github.fajaragungpramana.igit.module.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.domain.user.UserInteractor
import com.github.fajaragungpramana.igit.core.domain.user.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var userInteractor: UserInteractor

    private lateinit var viewModel: DetailViewModel

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = DetailViewModel(userInteractor)
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
    fun `getUser success should set state to UserData`() = runTest {
        val expected = User(
            avatar = "https://avatars.githubusercontent.com/u/47925662?s=400&u=e15d06caa4f49c9427a080c02f03b86f250f8a90&v=4",
            username = "fajaragungpramana",
            fullName = "Fajar Agung Pramana",
            bio = "Tech|Mobile Engineer"
        )
        val request = "fajaragungpramana"
        `when`(userInteractor.getUser(request)).thenReturn(
            channelFlow {
                send(
                    AppResult.Success(
                        expected
                    )
                )
            }
        )

        viewModel.setEvent(DetailEvent.User(request))

        val resultLoading = viewModel.state.first()
        Assert.assertTrue(resultLoading is DetailState.UserLoading)

        val resultSuccess = viewModel.state.first()
        Assert.assertTrue(resultSuccess is DetailState.UserData)
        Assert.assertEquals(expected, (resultSuccess as DetailState.UserData).user)
    }

}