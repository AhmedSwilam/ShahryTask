package com.shahry.com.eg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahry.com.eg.R
import com.shahry.com.eg.model.AuthorModel
import com.squareup.picasso.Picasso


class AuthorsAdapter : RecyclerView.Adapter<AuthorsAdapter.MyViewHolder>() {

    private var items = ArrayList<AuthorModel>()
    var onAuthorItemClicked:OnAuthorItemClicked?=null
    fun setUpdatedData(items: ArrayList<AuthorModel>) {
        this.items = items
        notifyDataSetChanged()
    }

   inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val authorImg:ImageView = itemView.findViewById(R.id.img_author)
        private val authorName:TextView = itemView.findViewById(R.id.txt_auth_name)
        private val authorUserName:TextView = itemView.findViewById(R.id.txt_auth_user_name)
        private val authorEmail:TextView = itemView.findViewById(R.id.txt_auth_email)

        fun bind(data: AuthorModel) {
            authorName.text = data.name.toString()
            authorUserName.text = data.userName.toString()
            authorEmail.text = data.email.toString()
            val imgUrl = data.avatarUrl.toString()
            Picasso.get()
                .load(imgUrl)
                .into(authorImg)
            itemView.setOnClickListener {
                onAuthorItemClicked?.onItemClicked(data)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.author_recycler_item, parent, false
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