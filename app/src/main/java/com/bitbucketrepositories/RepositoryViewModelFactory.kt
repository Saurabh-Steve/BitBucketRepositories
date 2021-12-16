package com.bitbucketrepositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitbucketrepositories.networking.DomainRepository
import io.reactivex.disposables.CompositeDisposable

object RepositoryViewModelFactory : ViewModelProvider.Factory {
    private var domainRepository = DomainRepository()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepositoryActivityObserver::class.java)) {
            return RepositoryActivityObserver(domainRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}