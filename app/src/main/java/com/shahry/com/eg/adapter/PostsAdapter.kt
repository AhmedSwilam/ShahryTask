package com.shahry.com.eg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahry.com.eg.R
import com.shahry.com.eg.model.PostsModel
import com.squareup.picasso.Picasso

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {

    private var items = ArrayList<PostsModel>()
    fun setPostsData(items: ArrayList<PostsModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPost: ImageView = itemView.findViewById(R.id.img_post)
        private val txtDate: TextView = itemView.findViewById(R.id.txt_date)
        private val txtTitle: TextView = itemView.findViewById(R.id.txt_title)
        private val txtBody: TextView = itemView.findViewById(R.id.txt_body)

        fun bind(data: PostsModel) {
            txtDate.text = data.date.toString()
            txtTitle.text = data.title.toString()
            txtBody.text = data.body.toString()
            val imgUrl = data.imageUrl.toString()
            Picasso.get()
                .load(imgUrl)
                .into(imgPost)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.posts_recycler_item,
            parent, false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}