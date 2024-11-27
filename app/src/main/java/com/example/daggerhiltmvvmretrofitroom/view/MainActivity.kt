package com.example.daggerhiltmvvmretrofitroom.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerhiltmvvmretrofitroom.Constants
import com.example.daggerhiltmvvmretrofitroom.databinding.ActivityMainBinding
import com.example.daggerhiltmvvmretrofitroom.model.data.ProductData
import com.example.daggerhiltmvvmretrofitroom.model.data.ProductRequest
import com.example.daggerhiltmvvmretrofitroom.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import okio.IOException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        saveProductDetails()
        setUpObservers()
        fetchList()
        viewPagerActivity()
    }

    private fun viewPagerActivity() {
        with(binding){
            btnViewPagerActivity.setOnClickListener {
                val viewPagerIntent = Intent(this@MainActivity,ViewPagerActivity::class.java)
                startActivity(viewPagerIntent)
            }
        }
    }

    private fun fetchList() {
        with(binding) {
            btnFetchList.setOnClickListener {
                val fetchIntent = Intent(this@MainActivity, FetchActivity::class.java)
                fetchIntent.putExtra(Constants.ID1,"ff808181932badb6019354fdb7836fdd")
                fetchIntent.putExtra(Constants.ID2, "ff808181932badb6019359b1561379ae")
                fetchIntent.putExtra(Constants.ID3, "ff808181932badb6019359b844c979b9")
                startActivity(fetchIntent)
            }
        }
    }

    private fun setUpObservers() {
        viewModel.productResponse.observe(this) {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Data Saved In Database Successfully")
                .setMessage("The Product saved in DB with Id ${it.id} and Name ${it.name}")
                .setPositiveButton("Close") { dialog, _ ->
                    dialog.dismiss()
                }.show()
            with(binding) {
                edtManuYear.text.clear()
                edtPrice.text.clear()
                edtCpu.text.clear()
                edtHardDisk.text.clear()
                edtModelName.text.clear()
            }
        }

        viewModel.error.observe(this){
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()

        }
    }


    private fun saveProductDetails() {
        binding.btnSubmit.setOnClickListener {
            with(binding) {
                val productData = ProductData(
                    edtManuYear.text.toString().toInt(),
                    edtPrice.text.toString().toDouble(),
                    edtCpu.text.toString(),
                    edtHardDisk.text.toString()

                )
                val name = edtModelName.text.toString()
                val productRequest = ProductRequest(name, productData)
                val product = viewModel.searchProductInDb(name)
                if (product == null) {
                    viewModel.syncLatestProduct(productRequest)
                } else {
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("Data Already Exist in database")
                        .setMessage("The Product available in DB with Id ${product.id} and Name ${product.modelName}")
                        .setPositiveButton("Close") { dialog, _ ->
                            dialog.dismiss()
                        }.show()
                    edtManuYear.text.clear()
                    edtPrice.text.clear()
                    edtCpu.text.clear()
                    edtHardDisk.text.clear()
                    edtModelName.text.clear()

                }

            }

        }


    }
}