package com.example.pharma_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StudyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        findViewById<Button>(R.id.btn_anatomi1).setOnClickListener {

            val intent = Intent(this, DetailActivity::class.java)

            intent.putExtra("english", "Anatomi 1 - Del 1")
            intent.putExtra("arabic", "تشريح 1")
            intent.putExtra(
                "url",
                "https://raw.githubusercontent.com/KSOM349/MedCore-Content/refs/heads/main/underskoterska/anatomi1/lesson_1_1.md"
            )

            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_anatomi2).setOnClickListener {

            val intent = Intent(this, DetailActivity::class.java)

            intent.putExtra("english", "Anatomi 1 - Del 2")
            intent.putExtra("arabic", "تشريح 2")
            intent.putExtra(
                "url",
                "https://raw.githubusercontent.com/KSOM349/MedCore-Content/refs/heads/main/underskoterska/anatomi1/lesson_1_2.md"
            )

            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_anatomi3).setOnClickListener {

            val intent = Intent(this, DetailActivity::class.java)

            intent.putExtra("english", "Anatomi 1 - Del 3")
            intent.putExtra("arabic", "تشريح 3")
            intent.putExtra(
                "url",
                "https://raw.githubusercontent.com/KSOM349/MedCore-Content/refs/heads/main/underskoterska/anatomi1/lesson_1_3.md"
            )

            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_anatomi4).setOnClickListener {

            val intent = Intent(this, DetailActivity::class.java)

            intent.putExtra("english", "Anatomi 1 - Del 4")
            intent.putExtra("arabic", "تشريح 4")
            intent.putExtra(
                "url",
                "https://raw.githubusercontent.com/KSOM349/MedCore-Content/refs/heads/main/underskoterska/anatomi1/lesson_1_4.md"
            )

            startActivity(intent)
        }
    }
}