package com.bitbucketrepositories

import com.bitbucketrepositories.networking.Repository

class RepositoryComparator constructor(val sortingFilter: String) :
    Comparator<Repository> {

    override fun compare(o1: Repository?, o2: Repository?): Int {
        return when (sortingFilter) {
//            SortingFilter.STARS -> ((o1 as Repository).stars.compareTo((o2 as Repository).stars))
//            SortingFilter.NAMES -> ((o1 as Repository).name.toUpperCase().compareTo((o2 as Repository).name.toUpperCase()))
            else -> 1
        }

    }

}