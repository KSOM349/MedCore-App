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
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.io.IOException

class DetailActivity : AppCompatActivity() {
    private val client = OkHttpClient()
    private var requestCall: Call? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        applyScreenInsets()
        val title = findViewById<TextView>(R.id.titleText)
        val description = findViewById<TextView>(R.id.descriptionText)
        val button = findViewById<Button>(R.id.startQuizButton)
        title.text = listOfNotNull(intent.getStringExtra("english"), intent.getStringExtra("arabic"))
            .filter { it.isNotBlank() }.joinToString(" — ")
        val lessonId = intent.getIntExtra("lesson_id", 0)
        button.visibility = if (QuizContent.anatomy(lessonId) != null) View.VISIBLE else View.GONE
        button.text = "Lesson quiz / اختبار الدرس"
        button.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java).putExtra("lesson_id", lessonId))
        }
        val url = intent.getStringExtra("url").orEmpty()
        if (url.isBlank()) {
            description.text = intent.getStringExtra("description") ?: "No lesson supplied / لم يحدد درس"
        } else {
            loadMarkdown(url, description)
        }
    }

    private fun loadMarkdown(url: String, textView: TextView) {
        val parsedUrl = url.toHttpUrlOrNull()
        if (parsedUrl == null || parsedUrl.scheme != "https") {
            textView.text = "Invalid lesson link / رابط الدرس غير صالح"
            return
        }
        textView.text = "Loading lesson / جار تحميل الدرس…"
        requestCall = client.newCall(Request.Builder().url(parsedUrl).build())
        requestCall!!.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (!call.isCanceled()) updateView {
                    textView.text = "Could not load lesson. Tap to retry. / تعذر تحميل الدرس. اضغط للمحاولة مجددًا."
                    textView.setOnClickListener { textView.setOnClickListener(null); loadMarkdown(url, textView) }
                }
            }
            override fun onResponse(call: Call, response: Response) {
                val body = response.use { if (it.isSuccessful) it.body?.string() else null }
                updateView {
                    if (body.isNullOrBlank()) {
                        textView.text = "Lesson unavailable. Tap to retry. / الدرس غير متاح. اضغط للمحاولة مجددًا."
                        textView.setOnClickListener { textView.setOnClickListener(null); loadMarkdown(url, textView) }
                    } else {
                        textView.text = body
                            .replace(Regex("^#{1,6}\\s*", RegexOption.MULTILINE), "")
                            .replace(Regex("<[^>]+>"), "")
                            .replace("---", "\n")
                        val image = findViewById<ImageView>(R.id.lessonImage)
                        val imagePath = Regex("<img\\s+[^>]*src=[\"']([^\"']+)[\"']", RegexOption.IGNORE_CASE)
                            .find(body)?.groupValues?.get(1)
                        val imageUrl = imagePath?.let { parsedUrl.resolve(it) }
                        if (imageUrl != null && imageUrl.scheme == "https" && imageUrl.host == parsedUrl.host) {
                            image.visibility = View.VISIBLE
                            image.contentDescription = titleForImage()
                            Glide.with(this@DetailActivity).load(imageUrl.toString()).into(image)
                        } else image.visibility = View.GONE
                    }
                }
            }
        })
    }
    private fun titleForImage() = intent.getStringExtra("english") ?: "Lesson illustration"
    private fun updateView(update: () -> Unit) = runOnUiThread {
        if (!isFinishing && !isDestroyed) update()
    }
    override fun onDestroy() {
        requestCall?.cancel()
        super.onDestroy()
    }
}