package com.kadder.mviexample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kader.mviexample.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showMainFragment()
    }

    private fun showMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment(), "MAIN_FRAGMENT")
            .commit()
    }


}























