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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class RepositoriesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindView(viewModel: RepositoryViewModel) {
        val imageView = view.findViewById<ImageView>(R.id.item_image)
        Glide
            .with(view.context)
            .load(viewModel.avatarUrl)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    imageView.setImageDrawable(view.context.getDrawable(R.drawable.solid_circle_green))
                    return true
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    imageView.setImageDrawable(resource)
                    return  true
                }
            })
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)

        view.findViewById<AppCompatTextView>(R.id.author).text = viewModel.display_name
        view.findViewById<AppCompatTextView>(R.id.reponame).text = viewModel.name
        view.findViewById<AppCompatTextView>(R.id.description).text = viewModel.description
        view.findViewById<AppCompatTextView>(R.id.language_text).text = viewModel.language
        view.findViewById<AppCompatTextView>(R.id.share_text).text = "Click for detail"

        view.findViewById<View>(R.id.expanded_view).visibility = VISIBLE

        view.setOnClickListener(View.OnClickListener {
             when (view.findViewById<View>(R.id.expanded_view).visibility) {
                 VISIBLE -> view.findViewById<View>(R.id.expanded_view).visibility = GONE
                 GONE -> view.findViewById<View>(R.id.expanded_view).visibility = VISIBLE
             }
        })

        view.findViewById<AppCompatImageView>(R.id.share_image).setOnClickListener { openUrl(view.context, viewModel.link) }
        view.findViewById<AppCompatTextView>(R.id.share_text).setOnClickListener { openUrl(view.context, viewModel.link) }
    }

    fun openUrl(context: Context, url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
        }
    }
}