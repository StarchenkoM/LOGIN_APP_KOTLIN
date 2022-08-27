package com.trd.loginapp.homescreen

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.trd.loginapp.Constants.PHONE_NUMBER_KEY
import com.trd.loginapp.R
import com.trd.loginapp.databinding.ActivityHomeScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        openProfileScreen()
        setContentView(binding.root)
    }

    private fun getPhoneNumber(): String? {
        val data = intent.extras
        val number = data?.getString(PHONE_NUMBER_KEY)
        Log.i("***888", "getPhoneNumber: data $data")
        Log.i("***888", "getPhoneNumber: numberFromIntent $number")
        return number
    }

    private fun openProfileScreen() {
        val action = HomeScreenDirections.openProfileScreen(getPhoneNumber())
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(action)
    }

}