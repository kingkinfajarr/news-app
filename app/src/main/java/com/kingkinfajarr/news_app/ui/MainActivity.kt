package com.kingkinfajarr.news_app.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kingkinfajarr.news_app.R
import com.kingkinfajarr.news_app.adapter.RecyclerViewNewsAdapter
import com.kingkinfajarr.news_app.models.News

class MainActivity : AppCompatActivity() {
    private lateinit var rvNews: RecyclerView
    private val list = ArrayList<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContentView(R.layout.activity_main)

        rvNews = findViewById(R.id.rv_news)
        rvNews.setHasFixedSize(true)

        list.addAll(getListNews())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_page -> {
                val intentDetail = Intent(this, AboutActivity::class.java)
                startActivity(intentDetail)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("Recycle")
    private fun getListNews(): ArrayList<News> {
        val title = resources.getStringArray(R.array.news_title)
        val description = resources.getStringArray(R.array.news_content)
        val date = resources.getStringArray(R.array.news_release_date)
        val link = resources.getStringArray(R.array.news_link)
        val image = resources.obtainTypedArray(R.array.news_images)
        val listNews = ArrayList<News>()
        for (i in title.indices) {
            val hero = News(title[i], description[i], date[i], link[i], image.getResourceId(i, -1))
            listNews.add(hero)
        }
        return listNews
    }

    private fun showRecyclerList() {
        rvNews.layoutManager = LinearLayoutManager(this)
        val listNewsAdapter = RecyclerViewNewsAdapter(list)
        rvNews.adapter = listNewsAdapter

        listNewsAdapter.setOnItemClickCallback(object: RecyclerViewNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: News) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("NEWS", data)
                startActivity(intent)
            }
        })
    }
}