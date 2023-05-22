package com.mendoza.endavacryptoapp.ui.market.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mendoza.endavacryptoapp.ui.market.usecase.CryptoCurrencyModel
import com.mendoza.endavacryptoapp.ui.market.usecase.FetchLatestCryptosUseCase
import com.mendoza.endavacryptoapp.ui.market.usecase.UseCaseResult
import com.mendoza.endavacryptoapp.ui.profile.usecase.GetUserFromGithubUseCase
import com.mendoza.endavacryptoapp.ui.profile.usecase.GithubProfileModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.lang.Exception

class MarketViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: MarketViewModel

    @Mock
    private lateinit var fetchLatestCryptosUseCase: FetchLatestCryptosUseCase

    @Mock
    private lateinit var getUserFromGithubUseCase: GetUserFromGithubUseCase

    private val dispatcher:CoroutineDispatcher = Dispatchers.Default

    val loadingViewObserver = mock(Observer::class.java) as Observer<Boolean>
    val errorViewObserver = mock(Observer::class.java) as Observer<Boolean>
    val cryptoCurrenciesListObserver = mock(Observer::class.java) as Observer<Boolean>
    val profileObserver = mock(Observer::class.java) as Observer<Boolean>

    @Before
    fun setup() {
        fetchLatestCryptosUseCase = mock(FetchLatestCryptosUseCase::class.java)
        getUserFromGithubUseCase = mock(GetUserFromGithubUseCase::class.java)
        viewModel = MarketViewModel(dispatcher, fetchLatestCryptosUseCase, getUserFromGithubUseCase)
    }

    @Test
    fun `Test fetchLatestCryptos when result is success`() = runBlocking(dispatcher) {
        val cryptosList = listOf(
            CryptoCurrencyModel(1,"Bitcoin","BTC",100.00,0.15,10000000.0)
        )
        `when`(fetchLatestCryptosUseCase.execute("USD")).thenReturn(UseCaseResult.Success(cryptosList))
        viewModel.fetchLatestCryptos("USD")

        Thread.sleep(100)

        assertEquals(false, viewModel.loadingView.value)
        assertEquals(false, viewModel.errorView.value)
        assertNotNull(viewModel.cryptoCurrenciesList.value)
        assertEquals(cryptosList, viewModel.cryptoCurrenciesList.value)
    }

    @Test
    fun `Test fetchLatestCryptos when result is error`() = runBlocking(dispatcher) {
        `when`(fetchLatestCryptosUseCase.execute("USD")).thenReturn(UseCaseResult.Error(Exception("Error Message")))

        viewModel.fetchLatestCryptos("USD")

        Thread.sleep(100)

        assertEquals(false, viewModel.loadingView.value)
        assertEquals(true, viewModel.errorView.value)
        assertNull(viewModel.cryptoCurrenciesList.value)
    }

    @Test
    fun `Test getProfileData when result is success`() = runBlocking(dispatcher) {
        val profileModel = GithubProfileModel(1,"username","avatar.com","Name","Location","Bio","profile.url.com")
        `when`(getUserFromGithubUseCase.execute("username")).thenReturn(UseCaseResult.Success(profileModel))

        viewModel.getProfileData("username")

        Thread.sleep(100)

        assertEquals(false, viewModel.loadingView.value)
        assertEquals(false, viewModel.errorView.value)
        assertNotNull(viewModel.profile.value)
        assertEquals(profileModel, viewModel.profile.value)
    }

    @Test
    fun `Test getProfileData when result is error`() = runBlocking(dispatcher) {
        `when`(getUserFromGithubUseCase.execute("username")).thenReturn(UseCaseResult.Error(Exception("Error Message")))

        viewModel.getProfileData("username")

        Thread.sleep(100)

        assertEquals(false, viewModel.loadingView.value)
        assertEquals(true, viewModel.errorView.value)
        assertNull(viewModel.profile.value)
    }
}