package com.example.pharma_app

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class SwedenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sweden)

        val laws = findViewById<LinearLayout>(R.id.lawsCard)
        val job = findViewById<LinearLayout>(R.id.jobCard)
        val family = findViewById<LinearLayout>(R.id.familyCard)
        val health = findViewById<LinearLayout>(R.id.healthCard)
        val government = findViewById<LinearLayout>(R.id.governmentCard)
        val mistakes = findViewById<LinearLayout>(R.id.mistakesCard)

        laws.setOnClickListener {
            startActivity(Intent(this, LawsActivity::class.java))
        }

        job.setOnClickListener {
            startActivity(Intent(this, JobActivity::class.java))
        }

        family.setOnClickListener {
            startActivity(Intent(this, FamilyActivity::class.java))
        }

        health.setOnClickListener {
            startActivity(Intent(this, HealthActivity::class.java))
        }

        government.setOnClickListener {
            startActivity(Intent(this, GovernmentActivity::class.java))
        }

        mistakes.setOnClickListener {
            startActivity(Intent(this, MistakesActivity::class.java))
        }
    }
}