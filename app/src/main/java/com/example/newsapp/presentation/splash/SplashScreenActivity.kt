package com.example.newsapp.presentation.splash

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.example.newsapp.R
import com.example.newsapp.presentation.fragment.boards.BoardActivity
import com.example.newsapp.presentation.main.MainActivity
import com.example.newsapp.util.SharedPrefManager
import java.util.*

class SplashScreenActivity : AppCompatActivity() {
    private val pref by lazy { SharedPrefManager(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        count()

        if (pref.getThemeIndex()?.index == true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        if (pref.getLanguage()?.isEnglish == true){
            changeLanguage(this, "en")
        }else{
            changeLanguage(this, "ru")
        }
    }

    fun count(){
        object : CountDownTimer(3000, 1000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                if (pref.getUser() != null){
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this@SplashScreenActivity, BoardActivity::class.java))
                    finish()
                }
            }
        }.start()
    }

    private fun changeLanguage(activity: Activity, langCode: String){
        val locale: Locale = Locale(langCode)
        Locale.setDefault(locale)
        val resources: Resources = activity.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}