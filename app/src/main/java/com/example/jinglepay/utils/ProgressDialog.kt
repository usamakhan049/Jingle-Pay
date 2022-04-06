package com.example.jinglepay.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.jinglepay.R

class ProgressDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        this.setContentView(R.layout.progress_dialog)
        this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}