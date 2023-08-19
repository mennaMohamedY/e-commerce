package com.example.myapplication.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R
import com.google.android.material.imageview.ShapeableImageView

class homeApplianceAdapter(val HomeAppliance_Data:ArrayList<Int>):Adapter<homeApplianceAdapter.MyHolder>() {

    var count=1
    class MyHolder(val itemView:View):ViewHolder(itemView){
        val home_applianceImg:ShapeableImageView
        val addToFavImg:ImageView
        init {
            home_applianceImg=itemView.findViewById(R.id.homeappliance_imgs)
            addToFavImg=itemView.findViewById(R.id.favourite_HomeFrag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.single_homeappliance_itemrv,parent,false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
       val imgRes= HomeAppliance_Data.get(position)
        holder.home_applianceImg.setImageResource(imgRes)

        holder.addToFavImg.setOnClickListener({
            if (count%2 != 0){
                holder.addToFavImg.setImageResource(R.drawable.colored_favourit)
                count+=1
            }else{
                holder.addToFavImg.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                count+=1
            }
        })
    }

    override fun getItemCount(): Int {
        return HomeAppliance_Data.size?:0
    }


}