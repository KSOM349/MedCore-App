package com.example.pharma_app

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class SubjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)
        applyScreenInsets()

        val btnPharma = findViewById<LinearLayout>(R.id.btnPharma)
        val btnAnatomy = findViewById<LinearLayout>(R.id.btnAnatomy)

        btnPharma.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        btnAnatomy.setOnClickListener {
            startActivity(Intent(this, StudyActivity::class.java))
        }
    }
}