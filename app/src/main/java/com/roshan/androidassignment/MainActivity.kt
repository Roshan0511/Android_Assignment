package com.roshan.androidassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.roshan.androidassignment.databinding.ActivityMainBinding
import com.roshan.androidassignment.homepage.RegistrationScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = RegistrationScreen()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.homePageFrame, fragment)
        transaction.commit()
    }
}