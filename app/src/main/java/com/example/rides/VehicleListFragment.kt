package com.example.rides

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rides.databinding.FragmentVehiclelistBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VehicleListFragment : Fragment() {

    private var _binding: FragmentVehiclelistBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentVehiclelistBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btSubmit.setOnClickListener {
            //string is empty or not
            if (binding.etInputval.text.isNotEmpty()) {
                if (binding.etInputval.text.toString().toInt() > 0) {
                    val service: ApiInterface = RetrofitClientInstance.retrofitInstance!!.create(
                        ApiInterface::class.java
                    )
                    val call: Call<List<VehicleClass?>?>? =
                        service.getAllPhotos(binding.etInputval.text.toString().toInt())
                    call!!.enqueue(object : Callback<List<VehicleClass?>?> {
                        override fun onResponse(
                            call: Call<List<VehicleClass?>?>,
                            response: Response<List<VehicleClass?>?>
                        ) {
                            //bind list
                            generateDataList(response.body(), activity)
                        }

                        override fun onFailure(call: Call<List<VehicleClass?>?>, t: Throwable) {

                        }
                    })
                }
            } else {
                Toast.makeText(activity, "Please enter valid input", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun generateDataList(
        photoList: List<VehicleClass?>?,
        activity: Activity?
    ) {
        //sort list
        val finallist: List<VehicleClass?> = photoList?.sortedBy { it!!.vin }!!
        val vehicleAdapter = activity?.let { VehicleAdapter(it, finallist) }
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rvVehiclelist.layoutManager = layoutManager
        binding.rvVehiclelist.adapter = vehicleAdapter
        vehicleAdapter?.setViewItemInterface(object : RecyclerViewItemInterface {
            override fun onItemClick(adapterPosition: Int, photoList: List<VehicleClass?>) {
                val bundle = bundleOf(
                    "vin" to photoList[adapterPosition]!!.vin,
                    "make_and_model" to photoList[adapterPosition]!!.make_and_model,
                    "color" to photoList[adapterPosition]!!.color,
                    "car_type" to photoList[adapterPosition]!!.car_type
                )
                binding.etInputval.setText("")
                //navigate to next screen
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
