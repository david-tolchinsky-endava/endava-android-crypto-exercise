package com.mendoza.endavacryptoapp.ui.market.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mendoza.endavacryptoapp.ui.market.usecase.CryptoCurrencyModel
import com.mendoza.endavacryptoapp.ui.market.usecase.FetchLatestCryptosUseCase
import com.mendoza.endavacryptoapp.ui.market.usecase.UseCaseResult
import com.mendoza.endavacryptoapp.ui.profile.usecase.GetUserFromGithubUseCase
import com.mendoza.endavacryptoapp.ui.profile.usecase.GithubProfileModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarketViewModel(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val fetchLatestCryptosUseCase: FetchLatestCryptosUseCase,
    private val getUserFromGithubUseCase: GetUserFromGithubUseCase
) : ViewModel() {
    private val _loadingView:MutableLiveData<Boolean> = MutableLiveData()
    val loadingView:LiveData<Boolean> = _loadingView

    private val _errorView:MutableLiveData<Boolean> = MutableLiveData()
    val errorView:LiveData<Boolean> = _errorView

    private val _cryptoCurrenciesList: MutableLiveData<List<CryptoCurrencyModel>> = MutableLiveData()
    val cryptoCurrenciesList: LiveData<List<CryptoCurrencyModel>> = _cryptoCurrenciesList

    private val _profile:MutableLiveData<GithubProfileModel> = MutableLiveData()
    val profile:LiveData<GithubProfileModel> = _profile

    @SuppressLint("NullSafeMutableLiveData")
    fun fetchLatestCryptos(selectedCurrency: String) {
        _loadingView.postValue(true)
        viewModelScope.launch(ioDispatcher) {
            when(val response = fetchLatestCryptosUseCase.execute(selectedCurrency)) {
                is UseCaseResult.Success -> {
                    _loadingView.postValue(false)
                    _errorView.postValue(false)
                    _cryptoCurrenciesList.postValue(response.data)
                }
                is UseCaseResult.Error -> {
                    _loadingView.postValue(false)
                    _errorView.postValue(true)
                }
            }
        }
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun getProfileData(username: String) {
        _loadingView.postValue(true)
        viewModelScope.launch(ioDispatcher) {
            when(val response = getUserFromGithubUseCase.execute(username)) {
                is UseCaseResult.Success -> {
                    _loadingView.postValue(false)
                    _errorView.postValue(false)
                    _profile.postValue(response.data)
                }
                is UseCaseResult.Error -> {
                    _loadingView.postValue(false)
                    _errorView.postValue(true)
                }
            }
        }
    }
}