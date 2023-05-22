package com.mendoza.endavacryptoapp.ui.profile.usecase

import com.mendoza.endavacryptoapp.ui.market.usecase.UseCaseResult
import com.mendoza.endavacryptoapp.ui.profile.datasource.repository.IGithubRepository

class GetUserFromGithubUseCase(private val repository: IGithubRepository) {

    suspend fun execute(username:String):UseCaseResult<GithubProfileModel> {
        return try {
            val response = repository.getUserData(username)
            UseCaseResult.Success(response.toGithubModel())
        }catch (error: Exception) {
            UseCaseResult.Error(error)
        }
    }
}