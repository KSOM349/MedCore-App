package com.example.pharma_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = findViewById<TextView>(R.id.titleText)
        val description = findViewById<TextView>(R.id.descriptionText)
        val button = findViewById<Button>(R.id.startQuizButton)

        val english = intent.getStringExtra("english")
        val arabic = intent.getStringExtra("arabic")
        val desc = intent.getStringExtra("description")

        title.text = "$english - $arabic"
        description.text = desc

        button.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}