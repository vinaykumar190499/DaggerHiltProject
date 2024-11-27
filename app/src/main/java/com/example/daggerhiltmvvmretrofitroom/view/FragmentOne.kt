package com.example.daggerhiltmvvmretrofitroom.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
//import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.daggerhiltmvvmretrofitroom.R
import com.example.daggerhiltmvvmretrofitroom.databinding.ActivityViewPagerBinding
import com.example.daggerhiltmvvmretrofitroom.databinding.FragmentOneBinding
import com.example.daggerhiltmvvmretrofitroom.viewmodel.ViewPagerViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentOne : Fragment() {

    lateinit var binding: FragmentOneBinding
    val viewModel: ViewPagerViewModel by lazy {
        ViewModelProvider(requireActivity())[ViewPagerViewModel::class.java]
    }
//    val viewModel:ViewPagerViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)
//        return inflater.inflate(R.layout.fragment_one, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViexws()
        setUpObserversFun()
        inittransfer()
    }

    private fun inittransfer() {
        binding.tvFragOne.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.fragData.postValue(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.fragData.postValue(s.toString())
            }

        })
    }

    private fun initViexws() {
        binding.btnSubmit.setOnClickListener {
            val data = binding.tvFragOne.text.toString().trim()
            if(data.length>0){
                viewModel.fragData.postValue(data)
            }
        }
    }

    private fun setUpObserversFun() {
//        viewModel.fragData.observe(viewLifecycleOwner) { data ->
//            if (data != binding.tvFragOne.text.toString()) {
//                binding.tvFragOne.setText(data)
//            }
//        }
        viewModel.fragData.observe(requireActivity()){

            binding.tvTitle.text = it.toString()
            binding.tvFragOne.setText(it.toString())
        }
    }

}