package com.example.daggerhiltmvvmretrofitroom.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerhiltmvvmretrofitroom.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ViewPagerViewModel @Inject constructor(val iRepository: IRepository):ViewModel() {

    val fragData:MutableLiveData<String> = MutableLiveData<String>()

}