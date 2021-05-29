package com.bitbucketrepositories.networking

import io.reactivex.Observable
import retrofit2.http.GET

interface BitBucketService {
    @GET("repositories")
    fun getRepositories() : Observable<RepositoryResponse>
}