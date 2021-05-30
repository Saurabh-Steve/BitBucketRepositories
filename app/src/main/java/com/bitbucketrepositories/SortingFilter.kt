package com.bitbucketrepositories

import androidx.annotation.StringDef
import com.bitbucketrepositories.SortingFilter.Companion.DEFAULT
import com.bitbucketrepositories.SortingFilter.Companion.NAMES
import com.bitbucketrepositories.SortingFilter.Companion.STARS
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


@Retention(RetentionPolicy.CLASS)
@StringDef (DEFAULT, STARS, NAMES)
annotation class SortingFilter {
    companion object {
        const val DEFAULT = "DEFAULT"
        const val STARS = "STARS"
        const val NAMES = "NAMES"
    }
}

