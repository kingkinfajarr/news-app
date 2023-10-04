package com.kingkinfajarr.news_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kingkinfajarr.news_app.R
import com.kingkinfajarr.news_app.models.News

class RecyclerViewNewsAdapter(
    private val listItems: ArrayList<News>,
    ) : RecyclerView.Adapter<RecyclerViewNewsAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: News)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewNewsAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewNewsAdapter.ViewHolder, position: Int) {
        val (title, description, date, link, img) = listItems[position]
        holder.image.setImageResource(img)
        holder.title.text = title
        holder.description.text = description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listItems[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val description: TextView = itemView.findViewById(R.id.tv_description)
        val image: ImageView = itemView.findViewById(R.id.image_news)
    }

}