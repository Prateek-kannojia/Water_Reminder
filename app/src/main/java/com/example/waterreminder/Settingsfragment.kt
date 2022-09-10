package com.example.waterreminder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.example.waterreminder.databinding.FragmentHomeBinding
import com.example.waterreminder.databinding.FragmentSettingsBinding

class Settingsfragment : Fragment() {
    private var binding: FragmentSettingsBinding?=null
    private val _binding get() = binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentSettingsBinding.inflate(inflater,container,false)
        _binding.darkMode.setOnCheckedChangeListener{_,ischecked->
            if(ischecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }
        }
        return binding!!.root

    }
}