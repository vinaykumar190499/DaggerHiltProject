package com.example.daggerhiltmvvmretrofitroom.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.daggerhiltmvvmretrofitroom.R
import com.example.daggerhiltmvvmretrofitroom.databinding.ActivityViewPagerBinding
import com.example.daggerhiltmvvmretrofitroom.viewmodel.ViewPagerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerActivity : AppCompatActivity() {
    val viewModel:ViewPagerViewModel by viewModels()
    private lateinit var binding:ActivityViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ViewPagerAdapter(this)
        binding.viewpager.adapter = adapter
    }
}