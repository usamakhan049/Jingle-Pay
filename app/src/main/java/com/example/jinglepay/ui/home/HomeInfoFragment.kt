package com.example.jinglepay.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.jinglepay.R
import com.example.jinglepay.databinding.FragmentHomeInfoBinding


class HomeInfoFragment : Fragment() {

    private lateinit var binding: FragmentHomeInfoBinding
    private var viewmodel: HomeViewmodel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_info, container, false)
        viewmodel = (requireActivity() as HomeActivity).viewModel

        updateData()

        return binding.root
    }

    private fun updateData() {
        binding.tvName.text = "Name:  ${viewmodel?.selectedMapPin?.name}"
        binding.tvUsername.text = "Username:  ${viewmodel?.selectedMapPin?.username}"
        binding.tvLocation.text =
            "Location:  ${viewmodel?.selectedMapPin?.address?.geo?.lat} , ${viewmodel?.selectedMapPin?.address?.geo?.lng}"
    }
}