package com.example.jinglepay.utils

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

var progressDialog: ProgressDialog? = null

fun Context.showProgressDialog() {
    progressDialog = ProgressDialog(this)
    progressDialog!!.setCancelable(false)
    progressDialog!!.show();
}

fun Context.hideProgressDialog() {
    progressDialog?.let {
        it.dismiss()
    }
}

fun Context.pixelToDp(values: Float): Int {
    return (values / resources.displayMetrics.density).toInt()
}

fun Context.getViewColor(colorId: Int): Int {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        return getColor(colorId)
    else
        return resources.getColor(colorId)
}