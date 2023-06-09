package com.mendoza.endavacryptoapp.ui.market.module

import com.mendoza.endavacryptoapp.network.COIN_MARKET_API
import com.mendoza.endavacryptoapp.ui.market.datasource.repository.CoinMarketRepository
import com.mendoza.endavacryptoapp.ui.market.datasource.repository.ICoinMarketRepository
import com.mendoza.endavacryptoapp.ui.market.datasource.service.ICoinMarketService
import com.mendoza.endavacryptoapp.ui.market.usecase.FetchLatestCryptosUseCase
import com.mendoza.endavacryptoapp.ui.market.viewModel.MarketViewModel
import com.mendoza.endavacryptoapp.ui.profile.datasource.repository.IGithubRepository
import com.mendoza.endavacryptoapp.ui.profile.usecase.GetUserFromGithubUseCase
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import retrofit2.Retrofit

val marketModule: Module = module {
    viewModel {
        MarketViewModel(fetchLatestCryptosUseCase = get(), getUserFromGithubUseCase = get())
    }

    single { fetchLatestCryptosUseCaseProvider(get()) }

    single<ICoinMarketRepository> { CoinMarketRepository(get()) }

    single { provideCoinMarketService(get(named(COIN_MARKET_API))) }
}

fun provideCoinMarketService(retrofit: Retrofit): ICoinMarketService {
    return retrofit.create(ICoinMarketService::class.java)
}

fun fetchLatestCryptosUseCaseProvider(repository: ICoinMarketRepository): FetchLatestCryptosUseCase {
    return FetchLatestCryptosUseCase(repository)
}