package com.example.waterreminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.waterreminder.databinding.FragmentHomeBinding


class Homefragment : Fragment() {
    private var binding:FragmentHomeBinding?=null
    private val _binding get() = binding!!
    private lateinit var userViewmodel: User_ViewModel
    private var water=1
    private var intake=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        userViewmodel= ViewModelProvider(this)[User_ViewModel::class.java]
        userViewmodel.total_water.observe(viewLifecycleOwner,{
            water=it
        })
        updatewaterpercentage()
        userViewmodel.glass_type.observe(viewLifecycleOwner,{
            val glass_type=it
            _binding.addButton.setOnClickListener {
                val current_dranked_water=_binding.consumedWater.text.toString().trim().toInt()
                val new_dranked_water=(current_dranked_water+glass_type)
                _binding.consumedWater.text=new_dranked_water.toString()
                updatewaterpercentage()
                userViewmodel.save_intake(new_dranked_water)
            }
        })
        return binding!!.root
    }

    private fun updatewaterpercentage() {
        userViewmodel.intake.observe(viewLifecycleOwner,{
            val i=it
            _binding.consumedWater.text=i.toString()
            _binding.waterPercentage.max= water
            _binding.waterPercentage.progress= i.toInt()
        })

    }
}