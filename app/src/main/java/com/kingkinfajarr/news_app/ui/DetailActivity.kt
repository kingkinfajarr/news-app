package com.kingkinfajarr.news_app.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kingkinfajarr.news_app.R
import com.kingkinfajarr.news_app.models.News
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title: TextView = findViewById(R.id.tv_detail_title)
        val description: TextView = findViewById(R.id.tv_detail_description)
        val date: TextView = findViewById(R.id.tv_detail_date)
        val link: TextView = findViewById(R.id.tv_detail_link)
        val image: ImageView = findViewById(R.id.image_detail_news)
        val btnShare = findViewById<Button>(R.id.action_share)

        val getNews = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<News>("NEWS", News::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<News>("NEWS")
        }

        btnShare.setOnClickListener {
            val text = """
                *${getNews?.title}*
                
                ${getNews?.description}
                
                Read More: ${getNews?.link}
            """.trimIndent()

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        title.text = getNews?.title
        description.text = getNews?.description
        date.text = getNews?.date?.let { formatDate(it) }
        link.text = getNews?.link
        getNews?.image?.let { image.setImageResource(it) }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.US)

        val date: Date = inputFormat.parse(inputDate) ?: Date()
        return outputFormat.format(date)
    }
}