package com.example.pharma_app

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applyScreenInsets()

        findViewById<TextView>(R.id.btn_laws).setOnClickListener {
            startActivity(Intent(this, LawsActivity::class.java))
        }

        findViewById<TextView>(R.id.btn_job).setOnClickListener {
            startActivity(Intent(this, JobActivity::class.java))
        }

        findViewById<TextView>(R.id.btn_family).setOnClickListener {
            startActivity(Intent(this, FamilyActivity::class.java))
        }

        findViewById<TextView>(R.id.btn_health).setOnClickListener {
            startActivity(Intent(this, HealthActivity::class.java))
        }

        findViewById<TextView>(R.id.btn_government).setOnClickListener {
            startActivity(Intent(this, GovernmentActivity::class.java))
        }

        findViewById<TextView>(R.id.btn_mistakes).setOnClickListener {
            startActivity(Intent(this, MistakesActivity::class.java))
        }

        findViewById<TextView>(R.id.btn_study).setOnClickListener {
            startActivity(Intent(this, SubjectActivity::class.java))
        }
    }
}