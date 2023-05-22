package com.mendoza.endavacryptoapp.ui.profile.datasource.repository

import com.mendoza.endavacryptoapp.ui.profile.datasource.entity.GithubResponseEntity

interface IGithubRepository {
    suspend fun getUserData(username:String):GithubResponseEntity
}