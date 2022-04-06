package com.example.jinglepay.utils
import android.app.Activity
import androidx.navigation.Navigation
import com.example.jinglepay.R


class NavigationWrapper constructor(
    activity: Activity
) {
    private var navController = Navigation.findNavController(activity, R.id.nav_host_fragment)

    fun navigateFragment(id: Int) {
        navController.navigate(id)
    }
}