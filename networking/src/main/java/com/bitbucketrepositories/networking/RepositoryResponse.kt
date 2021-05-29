package com.bitbucketrepositories.networking

/**
 * Modals are made assuming the strings
 * and values are non nulls from webservice
 */
data class RepositoryResponse(val values: List<Value>,
                              val pageLen: Int) {

    data class Value (
        val links: Links,
        val type: String,
        val name: String,
        val language: String,
        val owner: Owner,
        val description:String
    )

    data class Links(
        val commits: UrlContainer,
        val html : UrlContainer
    )

    data class UrlContainer(
        val href: String
    )

    data class Owner (
        val display_name: String,
        val links: OwnerLink,
        val nickname: String?
    )

    data class OwnerLink (
        val avatar: UrlContainer
    )
}




