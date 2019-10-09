package com.sathish.examplelivedata.view.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sathish.examplelivedata.R
import com.sathish.examplelivedata.service.model.Blog
import com.sathish.examplelivedata.view.ui.MainActivity

 class BlogAdapter(getActivityContext: MainActivity, private val blog: ArrayList<Blog>) :    RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    lateinit var rootView: View
    var getActivityContext: Activity? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return ViewHolder(rootView)

    }

    override fun getItemCount(): Int {
        return blog!!.size
    }

    override fun onBindViewHolder(holder: BlogAdapter.ViewHolder, position: Int) {
        val item = blog!!.get(position)
        holder.txtView_Tittle.setText(item.title)
        holder.txtView_desc.setText(item.description)
        holder.txtView_link.setText(item.link)


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivThumbnail: ImageView = itemView.findViewById(R.id.ImgView_author)
        val txtView_Tittle: TextView = itemView.findViewById(R.id.txtView_Tittle)
        val txtView_desc: TextView = itemView.findViewById(R.id.txtView_desc)
        val txtView_link: TextView = itemView.findViewById(R.id.txtView_link)

    }

}