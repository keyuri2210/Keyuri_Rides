package com.example.rides

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rides.databinding.FragmentVehicledetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

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
        kilometer = arguments?.getInt("kilometrage")!!
        binding.btnEstimate.setOnClickListener {
            //show bottom sheet
            showBottomSheetDialog()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog)
        val txtcarbon = bottomSheetDialog.findViewById<TextView>(R.id.txtcarbon)
        val imgClose = bottomSheetDialog.findViewById<ImageView>(R.id.img_close)
        //calculation for estimate carbon
        estimatedemision(kilometer)
        txtcarbon?.text = "$kilometer units"
        imgClose!!.setOnClickListener { bottomSheetDialog.cancel() }
        bottomSheetDialog.show()
    }

    companion object {
        var kilometer: Int = 0
        fun estimatedemision(kilometers: Int): Int {
            if (kilometers != 0) {
                if (kilometers > 5000) {
                    val carbonunit: Int = ((kilometers - 5000) * 1.5).toInt()
                    val finalcarbon = carbonunit + 5000
                    kilometer = finalcarbon
                } else {
                    kilometer = kilometers
                }
            }
            return kilometer
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}