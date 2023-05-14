package com.example.newsapp.presentation.fragment.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.bumptech.glide.Glide
import com.example.data.database.InformDao
import com.example.data.database.InformDatabase
import com.example.domain.model.Inform
import com.example.domain.use_case.all.AllUseCase
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityDetailBinding
import com.example.newsapp.di.DatabaseModule
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        val inform = intent.getParcelableExtra<Inform>("inform")!!

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            lifecycleScope.launch{
                DatabaseModule.provideDatabase(this@DetailActivity).dao.saveNew(inform)
                Toast.makeText(this@DetailActivity, "Saved Successfully", Toast.LENGTH_SHORT).show()
            }
        }

        with(binding){
            Glide.with(imageView).load(inform.url).into(imageView)
            textTitle.text = inform.title
            textContent.text = inform.content
        }
    }
}