package com.mendoza.endavacryptoapp.network

import com.mendoza.endavacryptoapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val COIN_MARKET_API = "COIN_MARKET_API"
const val COIN_MARKET_CLIENT = "COIN_MARKET_CLIENT"

const val GITHUB_API = "GITHUB_API"
const val GITHUB_CLIENT = "GITHUB_CLIENT"

val networkModule: Module = module {
    single<Interceptor> { provideAPIAuthInterceptor() }
    single(named(COIN_MARKET_CLIENT)) { provideRetrofitClient(get(), get()) }
    single(named(COIN_MARKET_API)) { provideCoinAPIRetrofit(get(named(COIN_MARKET_CLIENT))) }

    single(named(GITHUB_CLIENT)) { provideGithubRetrofitClient(get()) }
    single(named(GITHUB_API)) { provideGithubAPIRetrofit(get(named(GITHUB_CLIENT))) }

    single { providerHttpLoggingInterceptor() }
}

fun provideAPIAuthInterceptor(): ApiInterceptor {
    return ApiInterceptor()
}

fun provideRetrofitClient(apiInterceptor:Interceptor, httpLoggingInterceptor:HttpLoggingInterceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(apiInterceptor)
    builder.addInterceptor(httpLoggingInterceptor)
    return builder.build()
}

fun provideCoinAPIRetrofit(retrofitClient: OkHttpClient):Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(retrofitClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideGithubRetrofitClient(httpLoggingInterceptor:HttpLoggingInterceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(httpLoggingInterceptor)
    return builder.build()
}

fun provideGithubAPIRetrofit(retrofitClient: OkHttpClient):Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.GITHUB_API_URL)
        .client(retrofitClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}
