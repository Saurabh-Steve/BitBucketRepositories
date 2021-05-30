package com.bitbucketrepositories

import com.bitbucketrepositories.networking.Repository

interface RepositoriesContract {
    interface IView {
        fun showLoading()
        fun hideLoading()
        fun showRepositories(repositories: List<Repository>)
        fun showError(throwable: Throwable)
    }

    interface IPresenter {
        fun showRepositories(view: IView)

        fun onPause()
    }
}