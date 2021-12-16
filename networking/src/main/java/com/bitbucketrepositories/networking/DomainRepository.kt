package com.bitbucketrepositories.networking

class DomainRepository  {

    suspend fun getBitBucketRepos(bitBucketService: BitBucketService = APIClient.getClient().create(BitBucketService::class.java)): List<Repository> {
        return bitBucketService.getRepositories()
            .values
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
            }.toList()
    }
}