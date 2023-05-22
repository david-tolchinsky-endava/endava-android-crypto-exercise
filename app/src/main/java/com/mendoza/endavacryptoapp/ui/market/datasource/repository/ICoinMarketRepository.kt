package com.mendoza.endavacryptoapp.ui.market.datasource.repository

import com.mendoza.endavacryptoapp.ui.market.datasource.entity.CoinMarketResponseEntity

interface ICoinMarketRepository {
    suspend fun getLatestCryptos(currency:String): CoinMarketResponseEntity
}