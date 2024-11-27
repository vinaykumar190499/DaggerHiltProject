package com.example.daggerhiltmvvmretrofitroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerhiltmvvmretrofitroom.IRepository
import com.example.daggerhiltmvvmretrofitroom.model.data.FetchProductResponse
import com.example.daggerhiltmvvmretrofitroom.model.data.FetchProductResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FetchViewModel @Inject constructor(val iRepository: IRepository):ViewModel() {

    private val _fetchProducts  = MutableLiveData<List<FetchProductResponseItem>>()
    val fetchProducts:LiveData<List<FetchProductResponseItem>> = _fetchProducts
    val _error = MutableLiveData<String>()
    val error:LiveData<String> = _error

    fun fetchProducts(ids:List<String>){
        val call  = iRepository.fetchProducts(ids)
        call.enqueue(object: Callback<List<FetchProductResponseItem>>{
            override fun onResponse(
                call: Call<List<FetchProductResponseItem>>,
                response: Response<List<FetchProductResponseItem>>
            ) {
                if(!response.isSuccessful){
                    _error.postValue(response.errorBody().toString())
                }
                else{
                    response.body()?.let {
                        _fetchProducts.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<FetchProductResponseItem>>, t: Throwable) {
                if(t is IOException)
                _error.postValue("Not Converted to the Internet")
            }

        })
    }

}