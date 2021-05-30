package com.bitbucketrepositories

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitbucketrepositories.databinding.ActivityMainBinding
import com.bitbucketrepositories.networking.Repository


class RepositoriesActivity : AppCompatActivity(), RepositoriesContract.IView {
    private lateinit var binding : ActivityMainBinding

    private var presenter: RepositoriesPresenter = RepositoriesPresenter()

    private lateinit var adapter: RepositoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.repositories.layoutManager = LinearLayoutManager(this)
        adapter = RepositoriesAdapter()
        binding.repositories.adapter = adapter
        presenter.showRepositories(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun showLoading() {
        binding.loader.hide()
        binding.loader.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.loader.hide()
        binding.loader.visibility = View.GONE
    }

    override fun showRepositories(repositories: List<Repository>) {
        adapter.setItems(repositories)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
    }
}
