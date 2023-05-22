package com.mendoza.endavacryptoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.mendoza.endavacryptoapp.R
import com.mendoza.endavacryptoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var menuItemSelected: BottomNavigationItemView
    private lateinit var menuItemIndicator: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navHostFragment =  supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navGraph = navHostFragment.navController.navInflater.inflate(R.navigation.mobile_navigation)
        val navController = navHostFragment.navController
        navController.setGraph(graph = navGraph, startDestinationArgs = null)
        navController.addOnDestinationChangedListener(this)

        navView.setupWithNavController(navController)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        val view = binding.navView.findViewById<View>(destination.id)
        view?.let {
            addIndicatorAndIcon(it as BottomNavigationItemView)
        }
    }

    private fun addIndicatorAndIcon(viewGroup: BottomNavigationItemView) {
        if (::menuItemSelected.isInitialized && ::menuItemIndicator.isInitialized) {
            menuItemSelected.removeView(menuItemIndicator)
        }
        menuItemSelected = viewGroup
        menuItemIndicator = LayoutInflater.from(this)
            .inflate(R.layout.bottom_navigation_indicator, viewGroup, false)
        viewGroup.addView(menuItemIndicator)

    }
}