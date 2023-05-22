package com.mendoza.endavacryptoapp.ui.profile.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mendoza.endavacryptoapp.ui.market.usecase.UseCaseResult
import com.mendoza.endavacryptoapp.ui.profile.usecase.GetUserFromGithubUseCase
import com.mendoza.endavacryptoapp.ui.profile.usecase.GithubProfileModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.lang.Exception

class ProfileViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher: CoroutineDispatcher = Dispatchers.Default

    @Mock
    private lateinit var viewModel: ProfileViewModel

    @Mock
    private lateinit var getUserFromGithubUseCase: GetUserFromGithubUseCase

    @Before
    fun setup() {
        getUserFromGithubUseCase = mock(GetUserFromGithubUseCase::class.java)
        viewModel = ProfileViewModel(dispatcher, getUserFromGithubUseCase)
    }

    @Test
    fun `Test getProfileData when result is success`() = runBlocking(dispatcher) {
        val profileModel = GithubProfileModel(1,"username","avatar.com","Name","Location","Bio","profile.url.com")
        Mockito.`when`(getUserFromGithubUseCase.execute("username")).thenReturn(UseCaseResult.Success(profileModel))

        viewModel.getProfileData("username")

        Thread.sleep(100)

        assertEquals(false, viewModel.loadingView.value)
        assertEquals(false, viewModel.errorView.value)
        assertNotNull(viewModel.profile.value)
        assertEquals(profileModel, viewModel.profile.value)
    }

    @Test
    fun `Test getProfileData when result is error`() = runBlocking(dispatcher) {
        Mockito.`when`(getUserFromGithubUseCase.execute("username"))
            .thenReturn(UseCaseResult.Error(Exception("Error Message")))

        viewModel.getProfileData("username")

        Thread.sleep(100)

        assertEquals(false, viewModel.loadingView.value)
        assertEquals(true, viewModel.errorView.value)
        assertNull(viewModel.profile.value)
    }
}