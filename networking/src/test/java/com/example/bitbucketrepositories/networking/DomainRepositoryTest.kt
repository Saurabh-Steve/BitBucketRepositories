package com.example.bitbucketrepositories.networking

import com.bitbucketrepositories.networking.RepositoryResponse
import com.bitbucketrepositories.networking.DomainRepository
import com.bitbucketrepositories.networking.BitBucketService
import com.bitbucketrepositories.networking.Repository
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DomainRepositoryTest {

    private var subject = DomainRepository()
    private var bitBucketService = mock(BitBucketService::class.java)

    @Test
    fun getTrendingRepositories_returnsStoredResponse() {
        val repositoryResponse = mock(RepositoryResponse::class.java)
        whenever(bitBucketService.getRepositories())
            .thenReturn(
                Observable.just(repositoryResponse))
        whenever(repositoryResponse.values)
            .thenReturn(
                listOf(
                RepositoryResponse.Value(
                    RepositoryResponse.Links(
                        RepositoryResponse.UrlContainer("http://hyperlink1.com"),
                        RepositoryResponse.UrlContainer("http://hyperlink2.com")
                    ),
                    "type",
                    "repo name",
                    "java",
                    RepositoryResponse.Owner(
                        "Saurabh",
                        RepositoryResponse.OwnerLink(
                            RepositoryResponse.UrlContainer("https://github.com/PatilShreyas.png")
                        ),
                        "steve"
                    ),
                    "description"
                )
            )
        )
        val actualObserver: TestObserver<List<Repository>> = subject.getBitBucketRepos(bitBucketService).test()

        actualObserver.assertValue { it[0].link == "http://hyperlink2.com" }
        actualObserver.assertValue { (it)[0].name == "repo name" }
        actualObserver.assertValue { (it)[0].display_name == "Saurabh" }
        actualObserver.assertValue { (it)[0].avatarUrl.equals("https://github.com/PatilShreyas.png") }
        actualObserver.assertValue { (it)[0].language == "java" }
        actualObserver.assertValue { (it)[0].description == "description" }
        actualObserver.assertValue { (it)[0].type == "type" }

        verify(bitBucketService, times(1)).getRepositories()
    }
}


