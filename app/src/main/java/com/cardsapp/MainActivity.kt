package com.cardsapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import com.cardsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        setStatusBarColor()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    private fun setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 23) {
            window.statusBarColor = getColor(R.color.black)
        }
    }
}