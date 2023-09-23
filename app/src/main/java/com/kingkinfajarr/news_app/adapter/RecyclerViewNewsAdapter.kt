package com.kingkinfajarr.news_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kingkinfajarr.news_app.R
import com.kingkinfajarr.news_app.models.News

class RecyclerViewNewsAdapter(
    private val context: Context,
    private val listItems: ArrayList<News>,
    ) : RecyclerView.Adapter<RecyclerViewNewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewNewsAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_splash, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewNewsAdapter.ViewHolder, position: Int) {
//
    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}