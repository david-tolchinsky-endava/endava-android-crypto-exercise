package com.mendoza.endavacryptoapp.ui.market.datasource.entity

import com.google.gson.annotations.SerializedName

data class CoinMarketResponseEntity(
    val status: ResponseStatus,
    val data: List<CryptoResponseData>
)

data class ResponseStatus(
    val timestamp: String,
    val error_code: Int?,
    val error_message: Any?,
    val elapsed: Int,
    val credit_count: Int,
    val notice: Any?
)

data class CryptoResponseData(
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    val last_updated: String,
    val date_added: String,
    val quote: QuoteCurrencies,
)

data class QuoteCurrencies(
    @SerializedName("USD")
    val usdQuote: QuoteData?,
    @SerializedName("ARS")
    val arsQuote: QuoteData?
)

data class QuoteData(
    val price: Double,
    val volume_24h: Double,
    val volume_change_24h: Double,
    val percent_change_24h: Double,
    val market_cap: Double
)
