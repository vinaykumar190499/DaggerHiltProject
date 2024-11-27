package com.example.daggerhiltmvvmretrofitroom

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.daggerhiltmvvmretrofitroom.model.Entity.Product
import com.example.daggerhiltmvvmretrofitroom.model.data.FetchProductResponse
import com.example.daggerhiltmvvmretrofitroom.model.data.FetchProductResponseItem
import com.example.daggerhiltmvvmretrofitroom.model.data.ProductRequest
import com.example.daggerhiltmvvmretrofitroom.model.data.ProductResponse
import com.example.daggerhiltmvvmretrofitroom.model.local.LocalRepository
import com.example.daggerhiltmvvmretrofitroom.model.remote.RemoteRepository
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    val localRepository: LocalRepository,
    val remoteRepository: RemoteRepository
) : IRepository {
    private val _productResponse = MutableLiveData<ProductResponse>()
    override val productResponse: LiveData<ProductResponse> = _productResponse
    private val _error = MutableLiveData<String>()
    override val error: LiveData<String> = _error
    override fun searchProductInDb(name:String):Product{
        return localRepository.searchProductInDb(name)
    }

    override fun fetchProducts(ids: List<String>): Call<List<FetchProductResponseItem>> {
        return remoteRepository.fetchProducts(ids)
    }

    override fun syncLatestProducts(productRequest: ProductRequest) {
        println("Size  =  ${localRepository.getProducts().size} ")
        val call = remoteRepository.postProductInServer(productRequest)

        call.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (!response.isSuccessful) {
                    _error.postValue("Error is : ${response.errorBody()?.toString()}")
                } else {
                    lateinit var product: Product
                    val productResponse:ProductResponse? = response.body()
                    _productResponse.postValue(productResponse)
                    response.body()?.let {
                        println("Response ${response.body()}")
                        val productData = it.productData
                        println("here ------------------------------------ ${it.id}")
                        if(productData!=null){
                            product = Product(
                                it.id,
                                it.name,
                                productData.year,
                                productData.price,
                                productData.cpuModel,
                                productData.hardDiskSize
                            )
                            localRepository.saveProduct(product)
                            println("Size  =  ${localRepository.getProducts().size} ")

                        }

                    }
                }
            }

            override fun onFailure(p0: Call<ProductResponse>, p1: Throwable) {
                if(p1 is IOException)
                    _error.postValue("Not Converted to the Internet")
                else
                    _error.postValue(p1.message)
            }

        })
    }

}