package com.example.daggerhiltmvvmretrofitroom.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerhiltmvvmretrofitroom.Constants
import com.example.daggerhiltmvvmretrofitroom.R
import com.example.daggerhiltmvvmretrofitroom.databinding.ActivityFetchBinding
import com.example.daggerhiltmvvmretrofitroom.viewmodel.FetchViewModel
import com.example.daggerhiltmvvmretrofitroom.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FetchActivity : AppCompatActivity() {
    private val viewModel: FetchViewModel by viewModels()
    private lateinit var binding: ActivityFetchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFetchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.fetchProducts.observe(this){
            with(binding){
                recyclerview.layoutManager=LinearLayoutManager(this@FetchActivity)
                recyclerview.adapter=ProductsAdapter(it)

            }
        }
        viewModel.error.observe(this){
            println("here ------------- ${it}")
            Toast.makeText(this, it.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews() {

        val ids: MutableList<String> = mutableListOf()
        intent.getStringExtra(Constants.ID1)?.let { ids.add(it) }
        intent.getStringExtra(Constants.ID2)?.let { ids.add(it) }
        intent.getStringExtra(Constants.ID3)?.let { ids.add(it) }
        viewModel.fetchProducts(ids)

    }
}