package com.mendoza.endavacryptoapp.ui.market.usecase

import com.mendoza.endavacryptoapp.ui.market.datasource.repository.ICoinMarketRepository

class FetchLatestCryptosUseCase(private val repository: ICoinMarketRepository) {

    suspend fun execute(currentCurrency:String):UseCaseResult<List<CryptoCurrencyModel>> {
        return try {
//            val response = repository.getLatestCryptos(currentCurrency)
//            UseCaseResult.Success(response.toCryptoList())
            UseCaseResult.Success(listOf())
        } catch (error:Exception) {
            UseCaseResult.Error(error)
        }
    }
}

sealed class UseCaseResult<out T>{
    data class Success<out T>(val data:T):UseCaseResult<T>()
    data class Error(val error:Exception):UseCaseResult<Nothing>()
}