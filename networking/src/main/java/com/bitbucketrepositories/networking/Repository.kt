package com.bitbucketrepositories.networking

import androidx.lifecycle.ViewModel

data class Repository (
    val link: String,
    val avatarUrl: String,
    val type: String,
    val name: String,
    val language: String,
    val display_name: String,
    val description: String
)
