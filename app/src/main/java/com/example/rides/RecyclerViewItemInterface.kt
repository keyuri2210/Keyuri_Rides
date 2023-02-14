package com.example.rides

interface RecyclerViewItemInterface {
    fun onItemClick(adapterPosition: Int, photoList: List<VehicleClass?>)
}