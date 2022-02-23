package com.learn.degger.movieappmddnl.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.learn.degger.movieappmddnl.R
import com.learn.degger.movieappmddnl.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var  bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            bind = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val nav: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navControler: NavController = nav.navController
        NavigationUI.setupWithNavController(bind.bottomNav, navControler)
        bind.bottomNav.background = null
    }


}