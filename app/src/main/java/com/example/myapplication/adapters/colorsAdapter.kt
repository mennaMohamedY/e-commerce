package com.example.myapplication.adapters

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.myapplication.R

class colorsAdapter(var color_dataClass:ArrayList<colorsDataClass>):Adapter<colorsAdapter.MyColors_Adapter>() {
    var setCheckOnSelectedInterface:setCheckOnSelected?=null
    var count=0
    var send_Position:sendPosition?=null


    class MyColors_Adapter(itemview:View): RecyclerView.ViewHolder(itemview){
        var constrain_layout:ConstraintLayout
        var selected_img:ImageView
        init {
            constrain_layout=itemview.findViewById(R.id.color_constrainLayout)
            selected_img=itemview.findViewById(R.id.check_imgView)
        }
    }
    fun UpdateData(color_dataClass: ArrayList<colorsDataClass>){
        this.color_dataClass=color_dataClass
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyColors_Adapter {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.single_color_item,parent,false)
        return MyColors_Adapter(view)
    }

    override fun onBindViewHolder(holder: MyColors_Adapter, position: Int) {
        var currentItem=color_dataClass.get(position)
        holder.constrain_layout.background.setColorFilter(
            holder.constrain_layout.context.getColor(currentItem.color),PorterDuff.Mode.SRC_ATOP
        )
        holder.selected_img.setImageResource(R.drawable.transparent)

        /*
        holder.selected_img.setOnClickListener({
            setCheckOnSelectedInterface?.setCheck(position)

            holder.selected_img.setImageResource(R.drawable.ic_baseline_check_3)
        })
        holder.selected_img.setOnClickListener({
            holder.selected_img.setImageResource(R.drawable.ic_baseline_check_3)
        })

         */
        /*
        holder.selected_img.setOnClickListener({
            holder.selected_img.visibility=View.VISIBLE
            holder.selected_img.visibility=View.VISIBLE
        })

         */

        holder.selected_img.setOnClickListener({
            if(count>0){
                UpdateData(color_dataClass)
                holder.selected_img.setImageResource(R.drawable.ic_baseline_check_3)
                count=0

            }else{
                holder.selected_img.setImageResource(R.drawable.ic_baseline_check_3)
                count+=1
                send_Position?.getPosition(position)
            }

        })


    }
    interface sendPosition{
        fun getPosition(position: Int):Int
    }

    interface setCheckOnSelected{
        fun setCheck(position: Int)
    }


    override fun getItemCount(): Int {
       return color_dataClass.size
    }
}