package com.bitbucketrepositories

import androidx.lifecycle.ViewModel
import com.bitbucketrepositories.networking.Repository

data class RepositoryViewModel(
    val link: String,
    val avatarUrl: String,
    val type: String,
    val name: String,
    val language: String,
    val display_name: String,
    val description: String
) : ViewModel() {
    constructor(repository: Repository) : this(
        link = repository.link,
        avatarUrl = repository.avatarUrl,
        type = repository.type,
        name = repository.name,
        language = repository.language,
        display_name = repository.display_name,
        description = repository.description
    )
}
