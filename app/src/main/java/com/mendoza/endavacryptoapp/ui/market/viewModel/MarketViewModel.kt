package com.mendoza.endavacryptoapp.ui.market.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mendoza.endavacryptoapp.ui.market.usecase.CryptoCurrencyModel
import com.mendoza.endavacryptoapp.ui.market.usecase.FetchLatestCryptosUseCase
import com.mendoza.endavacryptoapp.ui.market.usecase.UseCaseResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarketViewModel(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val fetchLatestCryptosUseCase: FetchLatestCryptosUseCase,
) : ViewModel() {
    private val _loadingView:MutableLiveData<Boolean> = MutableLiveData()
    val loadingView:LiveData<Boolean> = _loadingView

    private val _errorView:MutableLiveData<Boolean> = MutableLiveData()
    val errorView:LiveData<Boolean> = _errorView

    private val _cryptoCurrenciesList: MutableLiveData<List<CryptoCurrencyModel>> = MutableLiveData()
    val cryptoCurrenciesList: LiveData<List<CryptoCurrencyModel>> = _cryptoCurrenciesList

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
}