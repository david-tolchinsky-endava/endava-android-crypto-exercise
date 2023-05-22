package com.mendoza.endavacryptoapp.ui.profile.usecase

import com.mendoza.endavacryptoapp.ui.profile.datasource.entity.GithubResponseEntity

fun GithubResponseEntity.toGithubModel() = GithubProfileModel(
    id = id,
    username = login,
    avatarUrl = avatar_url ?: "",
    name = name,
    location = location,
    bio = bio
)