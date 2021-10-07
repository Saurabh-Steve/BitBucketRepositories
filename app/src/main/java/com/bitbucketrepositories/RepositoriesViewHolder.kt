package com.bitbucketrepositories

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bitbucketrepositories.databinding.ItemListviewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class RepositoriesViewHolder(private val binding:ItemListviewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(viewModel: RepositoryViewModel) {
        binding.vm = viewModel
        binding.expandedView.vm = viewModel
    }
}