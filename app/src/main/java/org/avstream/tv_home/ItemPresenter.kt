package org.avstream.tv_home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide

class ItemPresenter: Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_view, parent, false)
        val params = view.layoutParams
        params.width = getWidthInPercent(parent!!.context, 12)
        params.height = getHeightInPercent(parent!!.context, 32)
        return ViewHolder(view)
    }

    private fun getWidthInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.widthPixels ?: 0
        return (width * percent) / 100
    }

    private fun getHeightInPercent(context: Context, percent: Int): Int {
        val height = context.resources.displayMetrics.heightPixels ?: 0
        return (height * percent) / 100
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val content = item as? Detail
        val imageView = viewHolder?.view?.findViewById<ImageView>(R.id.img_poster)
        val url = "https://www.themoviedb.org/t/p/w500" + content?.poster_path
        Glide.with(viewHolder?.view?.context!!)
            .load(url)
            .into(imageView!!)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }

}