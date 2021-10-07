package com.bitbucketrepositories

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bitbucketrepositories.databinding.ItemListviewBinding

class RepositoriesAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var repositories : List<RepositoryViewModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_listview, parent, false)
        val binding = ItemListviewBinding.bind(view)
        return  RepositoriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RepositoriesViewHolder).bindView(repositories[position])
    }

    fun setItems(repos: List<RepositoryViewModel>) {
        repositories = repos
        notifyDataSetChanged()
    }
}