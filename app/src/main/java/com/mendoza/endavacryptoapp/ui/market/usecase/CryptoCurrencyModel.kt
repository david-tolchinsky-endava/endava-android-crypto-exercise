package com.mendoza.endavacryptoapp.ui.market.usecase

data class CryptoCurrencyModel(
    val id: Int,
    val name: String,
    val symbol: String,
    val quote: Double,
    val volumeChange24H: Double,
    val marketCapital: Double
)


