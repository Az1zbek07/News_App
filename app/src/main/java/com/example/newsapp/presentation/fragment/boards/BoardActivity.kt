package com.example.newsapp.presentation.fragment.boards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.R

class BoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
            supportActionBar?.hide()
    }
}