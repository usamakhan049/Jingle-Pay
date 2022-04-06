package com.example.jinglepay.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.jinglepay.R
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker


class MarkerInfoWindowAdapter(var context: Context) :
    InfoWindowAdapter {
    override fun getInfoWindow(arg0: Marker): View? {
        return null
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun getInfoContents(marker: Marker): View? {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.map_marker_info_window, null)
        val tvTitle = view.findViewById(R.id.tv_title) as TextView
        val seeMoreBtn = view.findViewById(R.id.btn_see_more) as Button
        tvTitle.text = marker.title
        return view
    }
}