package com.mendoza.endavacryptoapp.ui.profile.datasource.entity

data class GithubResponseEntity(
    val avatar_url: String?,
    val bio: String,
    val blog: String,
    val company: String?,
    val created_at: String,
    val email: String?,
    val html_url: String,
    val id: Int,
    val location: String,
    val login: String,
    val name: String,
    val repos_url: String,
    val twitter_username: String?,
    val type: String,
    val updated_at: String,
    val url: String
)