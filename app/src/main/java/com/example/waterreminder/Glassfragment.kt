package com.example.waterreminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.waterreminder.databinding.FragmentGlassBinding

class Glassfragment : Fragment() {
    private var binding: FragmentGlassBinding?=null
    private val _binding get() = binding!!
    private lateinit var userViewmodel: User_ViewModel
    var water=0
    var intake=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentGlassBinding.inflate(inflater,container,false)
        userViewmodel= ViewModelProvider(this)[User_ViewModel::class.java]
        userViewmodel.total_water.observe(viewLifecycleOwner,{
            water=it
            _binding.TotalWater.text=it.toString()
        })

        userViewmodel.intake.observe(viewLifecycleOwner,{
            intake=it
            _binding.TotalIntake.text=it.toString()
            if(intake>water){
                _binding.RemainingWaterTxt.text= 0.toString()

            }
            else{
                _binding.RemainingWaterTxt.text=(water-intake).toString()
            }
        })

        _binding.ml125.setOnClickListener {
            userViewmodel.save_glasstype(125)
        }
        _binding.ml250.setOnClickListener {
            userViewmodel.save_glasstype(250)
        }
        _binding.ml350.setOnClickListener {
            userViewmodel.save_glasstype(350)
        }
        _binding.ml500.setOnClickListener {
            userViewmodel.save_glasstype(500)
        }
        return binding!!.root
    }

}
