package com.bitbucketrepositories

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


object Convertor {

    fun onListItemClick(view: View, binding: ViewDataBinding) {
        val expandedView = binding.root
        when (expandedView.visibility) {
            View.VISIBLE -> expandedView.visibility = View.GONE
            View.GONE -> expandedView.visibility = View.VISIBLE
        }
    }

    fun openUrl(view: View, url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            view.context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
        }
    }

    @BindingAdapter("imageUrl")
    @JvmStatic fun setImageUrl(imageView: ImageView, avatarUrl: String?) {
        if(avatarUrl != null)
            Glide
                .with(imageView.context)
                .load(avatarUrl)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.solid_circle_green))
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
    }
}
