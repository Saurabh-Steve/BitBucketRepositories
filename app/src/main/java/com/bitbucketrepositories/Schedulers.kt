package com.bitbucketrepositories

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Schedulers {

    fun ioScheduler() : Scheduler {
        return Schedulers.io()
    }

    fun uiScheduler() : Scheduler {
        return AndroidSchedulers.mainThread()
    }
}