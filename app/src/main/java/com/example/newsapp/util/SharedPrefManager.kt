package com.example.newsapp.util

import android.content.Context
import com.example.domain.model.Language
import com.example.domain.model.ThemeIndex
import com.example.domain.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SharedPrefManager(context: Context) {
    private val pref = context.getSharedPreferences("My Pref", Context.MODE_PRIVATE)
    private val edit = pref.edit()
    val gson = Gson()

    fun saveUser(user: User){
        val text = gson.toJson(user)
        edit.putString("user", text)
        edit.apply()
    }

    fun getUser(): User?{
        val json = pref.getString("user", null)
        val type: Type = object : TypeToken<User>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveThemeIndex(themeIndex: ThemeIndex){
        val text = gson.toJson(themeIndex)
        edit.putString("theme", text)
        edit.apply()
    }

    fun getThemeIndex(): ThemeIndex?{
        val json = pref.getString("theme", null)
        val type: Type = object : TypeToken<ThemeIndex>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveLanguage(language: Language){
        val text = gson.toJson(language)
        edit.putString("language", text)
        edit.apply()
    }

    fun getLanguage(): Language?{
        val json = pref.getString("language", null)
        val type: Type = object : TypeToken<Language>() {}.type
        return gson.fromJson(json, type)
    }
}