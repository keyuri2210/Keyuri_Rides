package com.example.rides

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rides.VehicleAdapter.CustomViewHolder

class VehicleAdapter(
    context1: Activity,
    private val photoList: List<VehicleClass?>?
) : RecyclerView.Adapter<CustomViewHolder>() {
    private val context: Activity
    private var viewItemInterface: RecyclerViewItemInterface? = null

    init {
        context = context1
    }

    fun setViewItemInterface(viewItemInterface: RecyclerViewItemInterface?) {
        this.viewItemInterface = viewItemInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.row_vehicle, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CustomViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.txtVin.text = photoList!![position]!!.vin
        holder.txtMake.text = photoList[position]!!.make_and_model
        holder.mView.setOnClickListener {
            if (viewItemInterface != null) {
                viewItemInterface!!.onItemClick(holder.adapterPosition, photoList)
            }

        }
    }

    override fun getItemCount(): Int {
        return photoList!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class CustomViewHolder(val mView: View) : RecyclerView.ViewHolder(
        mView
    ) {
        val txtVin: TextView = mView.findViewById(R.id.txt_vin)
        val txtMake: TextView = mView.findViewById(R.id.txt_make)

    }
}