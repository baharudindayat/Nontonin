package com.baharudindayat.nontonin.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.baharudindayat.nontonin.R
import com.baharudindayat.nontonin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        navController.addOnDestinationChangedListener { _, _, _ ->
            binding.bottomNavigationView.visibility = View.VISIBLE
            binding.bottomNavigationView.setupWithNavController(navController)
        }
    }

}