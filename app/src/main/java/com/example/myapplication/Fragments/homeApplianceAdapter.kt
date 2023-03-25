package com.example.myapplication.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R
import com.google.android.material.imageview.ShapeableImageView

class homeApplianceAdapter(val HomeAppliance_Data:ArrayList<Int>):Adapter<homeApplianceAdapter.MyHolder>() {

    class MyHolder(val itemView:View):ViewHolder(itemView){
        val home_applianceImg:ShapeableImageView
        init {
            home_applianceImg=itemView.findViewById(R.id.homeappliance_imgs)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.single_homeappliance_itemrv,parent,false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
       val imgRes= HomeAppliance_Data.get(position)
        holder.home_applianceImg.setImageResource(imgRes)
    }

    override fun getItemCount(): Int {
        return HomeAppliance_Data.size?:0
    }

}