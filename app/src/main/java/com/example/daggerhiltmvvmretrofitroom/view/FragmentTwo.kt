package com.example.daggerhiltmvvmretrofitroom.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.daggerhiltmvvmretrofitroom.R
import com.example.daggerhiltmvvmretrofitroom.databinding.FragmentOneBinding
import com.example.daggerhiltmvvmretrofitroom.databinding.FragmentTwoBinding
import com.example.daggerhiltmvvmretrofitroom.viewmodel.ViewPagerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentTwo : Fragment() {

    lateinit var binding: FragmentTwoBinding

        val viewModel: ViewPagerViewModel by lazy {
        ViewModelProvider(requireActivity())[ViewPagerViewModel::class.java]
    }
//    val viewModel: ViewPagerViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        return inflater.inflate(R.layout.fragment_two, container, false)
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.fragData.observe(requireActivity()) {
            binding.tvTitle.text = it.toString()
            binding.tvFragTwo.setText(it.toString())
        }
    }

    private fun initViews() {
        binding.tvFragTwo.addTextChangedListener(object : TextWatcher {
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

}