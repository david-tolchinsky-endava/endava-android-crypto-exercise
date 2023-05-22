package com.mendoza.endavacryptoapp.ui.profile.module

import com.mendoza.endavacryptoapp.network.GITHUB_API
import com.mendoza.endavacryptoapp.ui.profile.datasource.repository.GithubRepository
import com.mendoza.endavacryptoapp.ui.profile.datasource.repository.IGithubRepository
import com.mendoza.endavacryptoapp.ui.profile.datasource.service.IGithubService
import com.mendoza.endavacryptoapp.ui.profile.usecase.GetUserFromGithubUseCase
import com.mendoza.endavacryptoapp.ui.profile.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val profileModule: Module = module {
    viewModel {
        ProfileViewModel(
            getUserFromGithubUseCase = provideGetUserFromGithubUseCase(get())
        )
    }

    single<IGithubRepository> { GithubRepository(get()) }

    single { provideGithubService(get(named(GITHUB_API))) }
}

fun provideGetUserFromGithubUseCase(repository: IGithubRepository): GetUserFromGithubUseCase {
    return GetUserFromGithubUseCase(repository)
}

fun provideGithubService(retrofit: Retrofit):IGithubService {
    return retrofit.create(IGithubService::class.java)
}
