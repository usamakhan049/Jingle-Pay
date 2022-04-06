package com.example.jinglepay.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(
            Context
                .INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}

inline fun <reified T : Activity> Context?.showActivity(
    bundle: Bundle? = null,
    isNewTask: Boolean = false
) {
    val intent = Intent(this, T::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        if (isNewTask)
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        else
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    bundle?.let {
        intent.putExtras(bundle)
    }
    this?.startActivity(intent)
}


fun Context?.isNetworkAvailable(): Boolean {
    var isAvailable = false
    val connectivityManager =
        this?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                isAvailable = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
    } else {
        connectivityManager.activeNetworkInfo.also {
            if (it != null && it.isConnected)
                isAvailable = true
        }
    }
    return isAvailable
}

fun AppCompatActivity.resultLauncher(calback: (intent: Intent?) -> Unit): ActivityResultLauncher<Intent> {
    return this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            calback(data)
        }
    }
}
