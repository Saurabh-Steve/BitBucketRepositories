package com.bitbucketrepositories

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitbucketrepositories.databinding.ActivityMainBinding


class RepositoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: RepositoriesAdapter

    private val repositoryActivityObserver: RepositoryActivityObserver
            by lazy {
                ViewModelProviders.of(this, RepositoryViewModelFactory)
                    .get(RepositoryActivityObserver::class.java)
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.repositoriesViewModel = repositoryActivityObserver
        binding.lifecycleOwner = this
        (binding.lifecycleOwner as RepositoriesActivity).lifecycle.addObserver(
            repositoryActivityObserver
        )

        binding.repositories.layoutManager = LinearLayoutManager(this)
        adapter = RepositoriesAdapter()
        binding.repositories.adapter = adapter

        repositoryActivityObserver.loading.observe(this, Observer {
            when (it) {
                true -> showLoading()
                else -> hideLoading()
            }
        })
        repositoryActivityObserver.repos.observe(this, Observer {
            hideLoading()
            adapter.setItems(it)
        })
    }

    private fun showLoading() {
        binding.loader.hide()
        binding.loader.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loader.hide()
        binding.loader.visibility = View.GONE
    }
}
