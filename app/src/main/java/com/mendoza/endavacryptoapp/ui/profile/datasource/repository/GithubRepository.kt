package com.mendoza.endavacryptoapp.ui.profile.datasource.repository

import com.mendoza.endavacryptoapp.ui.profile.datasource.entity.GithubResponseEntity
import com.mendoza.endavacryptoapp.ui.profile.datasource.service.IGithubService

class GithubRepository(private val service:IGithubService):IGithubRepository {
    override suspend fun getUserData(username:String): GithubResponseEntity {
        return service.getUserData(username)
    }
}