package com.example.jinglepay.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.jinglepay.R
import com.example.jinglepay.data.network.models.MapPin
import com.example.jinglepay.databinding.FragmentHomeMapBinding
import com.example.jinglepay.utils.MarkerInfoWindowAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class HomeMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentHomeMapBinding
    private var viewmodel: HomeViewmodel? = null
    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_map, container, false)
        viewmodel = (requireActivity() as HomeActivity).viewModel
        binding.viewmodel = viewmodel

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        oberverMapPins()
        return binding.root
    }

    private fun oberverMapPins() {
        viewmodel?.mapPinsliveData?.observe(viewLifecycleOwner, {
            addMapPins(it)
        })
    }

    private fun addMapPins(mapPins: ArrayList<MapPin>) {
        mapPins.forEach {
            googleMap?.addMarker(
                MarkerOptions().position(
                    LatLng(
                        it?.address?.geo?.lat?.toDouble()!!,
                        it?.address?.geo?.lng?.toDouble()!!
                    )
                ).title(it.name)
            )
        }
        if (mapPins.size < 15) {
            googleMap?.moveCamera(
                CameraUpdateFactory.newLatLng(
                    LatLng(
                        mapPins.get(0).address?.geo?.lat?.toDouble()!!,
                        mapPins.get(0).address?.geo?.lng?.toDouble()!!
                    )
                )
            )
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val markerInfoWindowAdapter =
            MarkerInfoWindowAdapter(requireContext())
        googleMap!!.setInfoWindowAdapter(markerInfoWindowAdapter)

        googleMap?.setOnInfoWindowClickListener { marker ->
            val mapPin = viewmodel?.mapPins?.find { it.name.equals(marker.title, true) }
            mapPin?.let {
                viewmodel?.onMapPinClicked(it)
            }
        }
    }
}