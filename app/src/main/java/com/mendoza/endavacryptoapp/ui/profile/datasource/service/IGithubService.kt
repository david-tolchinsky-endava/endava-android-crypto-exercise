package com.mendoza.endavacryptoapp.ui.profile.datasource.service

import com.mendoza.endavacryptoapp.ui.profile.datasource.entity.GithubResponseEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface IGithubService {
    @GET("users/{username}")
    suspend fun getUserData(@Path("username") username:String):GithubResponseEntity
}