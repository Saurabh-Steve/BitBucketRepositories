package com.bitbucketrepositories.networking

import io.reactivex.Observable

class DomainRepository  {

     fun getBitBucketRepos(bitBucketService: BitBucketService = APIClient.getClient().create(BitBucketService::class.java)): Observable<List<Repository>> {
        return bitBucketService.getRepositories()
            .flatMap {
            Observable.fromIterable<RepositoryResponse.Value>(it.values)
        }
            .map {
                Repository(
                    it.links.html.href,
                    it.owner.links.avatar.href,
                    it.type,
                    it.name,
                    it.language,
                    it.owner.display_name,
                    it.description
                )
            }.toList().toObservable()
    }
}