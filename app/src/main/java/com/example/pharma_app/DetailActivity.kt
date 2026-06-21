package com.example.pharma_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import okhttp3.*
import java.io.IOException

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = findViewById<TextView>(R.id.titleText)
        val description = findViewById<TextView>(R.id.descriptionText)
        val button = findViewById<Button>(R.id.startQuizButton)

        val english = intent.getStringExtra("english") ?: ""
        val arabic = intent.getStringExtra("arabic") ?: ""
        val url = intent.getStringExtra("url") ?: ""

        title.text = "$english - $arabic"
        description.text = "Loading..."

        loadMarkdown(url, description)

        button.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }
    }

    private fun loadMarkdown(url: String, textView: TextView) {

        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    textView.text = e.message
                }
            }

            override fun onResponse(call: Call, response: Response) {

                val body = response.body?.string()

                runOnUiThread {

                    val cleanContent = (body ?: "")
                        .replace(Regex("^####\\s*", RegexOption.MULTILINE), "")
                        .replace(Regex("^###\\s*", RegexOption.MULTILINE), "")
                        .replace(Regex("^##\\s*", RegexOption.MULTILINE), "")
                        .replace(Regex("^#\\s*", RegexOption.MULTILINE), "")
                        .replace("---", "\n")
                        .replace(Regex("<div.*?>", RegexOption.IGNORE_CASE), "")
                        .replace(Regex("<img.*?>", RegexOption.IGNORE_CASE), "")
                        .replace("</div>", "")
                    val lessonImage =
                        findViewById<ImageView>(R.id.lessonImage)

                    if (body?.contains("<img") == true) {

                        val imageUrl =
                            "https://raw.githubusercontent.com/KSOM349/MedCore-Content/main/underskoterska/anatomi1/images/cell_och_vavnad.png.jpeg"

                        lessonImage.visibility = View.VISIBLE

                        Glide.with(this@DetailActivity)
                            .load(imageUrl)
                            .into(lessonImage)
                    }
                    textView.text = cleanContent
                }
            }
        })
    }
}