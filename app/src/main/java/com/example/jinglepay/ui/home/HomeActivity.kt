package com.example.jinglepay.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jinglepay.R
import com.example.jinglepay.data.network.models.MapPin
import com.example.jinglepay.databinding.ActivityHomeBinding
import com.example.jinglepay.interfaces.IHomeScreenListner
import com.example.jinglepay.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), IHomeScreenListner {

    val viewModel: HomeViewmodel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private var navigationWrapper: NavigationWrapper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel.iHomeScreenListner = this
        navigationWrapper = NavigationWrapper(this)

        viewModel.getMapPins()
    }

    override fun onMapPinClicked() {
        navigationWrapper?.navigateFragment(R.id.action_homeMapFragment_to_homeInfoFragment)
    }

    override fun onStarted() {
    }

    override fun onSuccess(result: ArrayList<MapPin>) {
    }

    override fun onFailure(message: String) {
    }
}