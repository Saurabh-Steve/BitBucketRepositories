package com.bitbucketrepositories

import androidx.lifecycle.*
import com.bitbucketrepositories.networking.DomainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async

class RepositoryActivityObserver(
    private val domainRepository: DomainRepository,
) : ViewModel(), LifecycleObserver {

    var repos: MutableLiveData<List<RepositoryViewModel>> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        viewModelScope.async (Dispatchers.IO + Job()) {
            loading.postValue(true)
            try {
                repos.postValue(domainRepository.getBitBucketRepos()
                    .map {
                        RepositoryViewModel(it)
                    })
            } catch(t: Throwable) {
                print("something went wrong" + t.localizedMessage)
            } finally {
                loading.postValue(false)
            }
        }
    }
}