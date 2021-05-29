package com.bitbucketrepositories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class RepositoriesActivity : AppCompatActivity() {
    private var presenter: RepositoriesPresenter = RepositoriesPresenter()

    private lateinit var adapter: RepositoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        adapter = RepositoriesAdapter()
    }

}
