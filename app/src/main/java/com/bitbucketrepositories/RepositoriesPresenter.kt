package com.bitbucketrepositories

import com.bitbucketrepositories.networking.DomainRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable


class RepositoriesPresenter(
    private var schedulers: Schedulers,
    private var domainRepository: DomainRepository,
    private var compositeDisposable: CompositeDisposable
) : RepositoriesContract.IPresenter {

constructor() : this(Schedulers(), DomainRepository(), CompositeDisposable())

    override fun showRepositories(view: RepositoriesContract.IView) {
        val subscribe = domainRepository
            .getBitBucketRepos()
            .subscribeOn(schedulers.ioScheduler())
            .observeOn(schedulers.uiScheduler())
            .doOnSubscribe { view.showLoading() }
            .doAfterTerminate { view.hideLoading() }
            .subscribe ({ view.showRepositories(it) }, { error -> view.showError(error)})
        compositeDisposable.add(subscribe)
    }

    override fun onPause() {
       compositeDisposable.dispose()
    }

}
