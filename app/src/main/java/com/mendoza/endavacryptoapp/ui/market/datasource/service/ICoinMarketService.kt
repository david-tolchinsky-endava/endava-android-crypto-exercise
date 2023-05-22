package com.mendoza.endavacryptoapp.ui.market.datasource.service

import com.mendoza.endavacryptoapp.ui.market.datasource.entity.CoinMarketResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ICoinMarketService {
    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getLatestCryptos(@Query("start") start:Int, @Query("limit") limit:Int, @Query("convert") currency:String): CoinMarketResponseEntity
}