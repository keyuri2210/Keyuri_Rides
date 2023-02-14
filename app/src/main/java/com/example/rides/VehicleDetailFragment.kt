package com.example.rides

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rides.databinding.FragmentVehicledetailBinding

class VehicleDetailFragment : Fragment() {

    private var _binding: FragmentVehicledetailBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentVehicledetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //fetch value from previous screen
        binding.txtVin.text = arguments?.getString("vin")
        binding.txtCartype.text = arguments?.getString("car_type")
        binding.txtMake.text = arguments?.getString("make_and_model")
        binding.txtColor.text = arguments?.getString("color")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}