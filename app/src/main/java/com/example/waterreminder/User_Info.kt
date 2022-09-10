package com.example.waterreminder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.waterreminder.databinding.ActivityUserInfoBinding


class User_Info : AppCompatActivity() {
    private lateinit var  binding:ActivityUserInfoBinding
    private lateinit var userDetails: UserDetails
    private lateinit var userViewmodel: User_ViewModel
    private var hasSeenIntro:Boolean=true
    private var weight= 0
    private var age= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userDetails= UserDetails(this)
        userViewmodel= ViewModelProvider(this)[User_ViewModel::class.java]
        binding.Continue.setOnClickListener{
            weight=binding.userWeight.text.toString().trim().toInt()
            age=binding.userAge.text.toString().trim().toInt()
            userViewmodel.save_Data(weight,age)
            userViewmodel.save_intro(hasSeenIntro)
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        userViewmodel.hasSeenIntro.observe(this,{
            if(it){
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        })
    }
}