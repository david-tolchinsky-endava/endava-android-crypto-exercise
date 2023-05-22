package com.mendoza.endavacryptoapp.ui.market.usecase

import com.mendoza.endavacryptoapp.ui.market.datasource.entity.CoinMarketResponseEntity

fun CoinMarketResponseEntity.toCryptoList():List<CryptoCurrencyModel> {
    return this.data.map { cryptoObject -> CryptoCurrencyModel(
        id = cryptoObject.id,
        name = cryptoObject.name,
        symbol = cryptoObject.symbol,
        quote = cryptoObject.quote.usdQuote?.price ?: cryptoObject.quote.arsQuote?.price ?: 0.0,
        volumeChange24H = cryptoObject.quote.usdQuote?.percent_change_24h ?: cryptoObject.quote.arsQuote?.percent_change_24h ?: 0.0,
        marketCapital = cryptoObject.quote.usdQuote?.market_cap ?: cryptoObject.quote.arsQuote?.market_cap ?: 0.0
    )}
}