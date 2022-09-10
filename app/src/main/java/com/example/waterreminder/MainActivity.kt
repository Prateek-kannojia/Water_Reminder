package com.example.waterreminder

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.waterreminder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewmodel: User_ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userViewmodel=ViewModelProvider(this)[User_ViewModel::class.java]
        val homefragment= Homefragment()
        val glassfragment=Glassfragment()
        val settingsfragment=Settingsfragment()
        fragmentchange(homefragment)
        binding.bottomNavBar.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.Home->fragmentchange(homefragment)
                R.id.Glass->fragmentchange(glassfragment)
                R.id.Settings->fragmentchange(settingsfragment)
            }
            true
        }
    }

    private fun fragmentchange(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fillFragment.id,fragment)
            commit()
        }
    }
}