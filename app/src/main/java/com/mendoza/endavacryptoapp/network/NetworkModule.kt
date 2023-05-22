package com.mendoza.endavacryptoapp.network

import com.mendoza.endavacryptoapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule: Module = module {
    single<Interceptor> { provideAPIAuthInterceptor() }
    single { provideRetrofitClient(get()) }
    single { provideRetrofit(get()) }
}

fun provideAPIAuthInterceptor(): ApiInterceptor {
    return ApiInterceptor()
}

fun provideRetrofitClient(apiInterceptor:Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(apiInterceptor)
    return builder.build()
}

fun provideRetrofit(retrofitClient: OkHttpClient):Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(retrofitClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
