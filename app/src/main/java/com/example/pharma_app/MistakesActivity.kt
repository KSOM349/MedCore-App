package com.example.pharma_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MistakesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SwedenGuide.show(this, "mistakes")
    }
}