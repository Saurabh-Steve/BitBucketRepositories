package com.bitbucketrepositories

import androidx.lifecycle.*
import com.bitbucketrepositories.networking.DomainRepository
import io.reactivex.disposables.CompositeDisposable

class RepositoryActivityObserver(
    private val schedulers: Schedulers,
    private val domainRepository: DomainRepository,
    private val compositeDisposable: CompositeDisposable
) : ViewModel(), LifecycleObserver {

    var repos: MutableLiveData<List<RepositoryViewModel>> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        val subscribe = domainRepository
            .getBitBucketRepos()
            .subscribeOn(schedulers.ioScheduler())
            .observeOn(schedulers.uiScheduler())
            .doOnSubscribe {
                loading.value = true
            }
            .map { it ->
                it.map {
                    RepositoryViewModel(it)
                }
            }.doOnError {
                loading.value = false
            }.subscribe {
                loading.value = false
                repos.value = it
            }
        compositeDisposable.add(subscribe)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}