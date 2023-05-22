package com.mendoza.endavacryptoapp.ui.market.datasource.repository

import com.mendoza.endavacryptoapp.ui.market.datasource.entity.CoinMarketResponseEntity
import com.mendoza.endavacryptoapp.ui.market.datasource.service.ICoinMarketService

class CoinMarketRepository(val service: ICoinMarketService):ICoinMarketRepository {
    override suspend fun getLatestCryptos(currency:String): CoinMarketResponseEntity {
        return service.getLatestCryptos(1, 30, currency)
    }
}