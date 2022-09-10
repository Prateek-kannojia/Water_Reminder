package com.example.waterreminder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class User_ViewModel(application: Application):AndroidViewModel(application) {
    private val repository=UserDetails(application)
    val hasSeenIntro=repository.hasSeenIntro.asLiveData()
    val total_water=repository.water.asLiveData()
    val glass_type=repository.glasstype.asLiveData()
    val intake=repository.intake.asLiveData()
    fun save_Data(weight:Int,age:Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.storedata(weight,age)
        }
    }
    fun save_intro(hasSeenIntro:Boolean){
        viewModelScope.launch(Dispatchers.IO){
            repository.storeHasSeenIntro(hasSeenIntro)
        }
    }
    fun save_glasstype(glasstype:Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.storeglasstype(glasstype)
        }
    }
    fun save_intake(intake:Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.Intake(intake)
        }
    }

}