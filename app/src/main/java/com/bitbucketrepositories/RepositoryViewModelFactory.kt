package com.bitbucketrepositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitbucketrepositories.networking.DomainRepository
import io.reactivex.disposables.CompositeDisposable

object RepositoryViewModelFactory : ViewModelProvider.Factory {
    private var schedulers = Schedulers()
    private var domainRepository = DomainRepository()
    private var compositeDisposable = CompositeDisposable()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepositoryActivityObserver::class.java)) {
            return RepositoryActivityObserver(schedulers, domainRepository, compositeDisposable) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}