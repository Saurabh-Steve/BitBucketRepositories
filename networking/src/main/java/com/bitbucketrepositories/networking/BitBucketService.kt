package com.bitbucketrepositories.networking

import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import org.intellij.lang.annotations.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface BitBucketService {
    @GET("repositories")
    suspend fun getRepositories() : RepositoryResponse
}