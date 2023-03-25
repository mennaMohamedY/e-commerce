package com.example.myapplication.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R
import com.google.android.material.imageview.ShapeableImageView

class categoryAdapter(val categorydataClass:ArrayList<categoryDataClass>):Adapter<categoryAdapter.categoryHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.single_category_item_rv,parent,false)
        return categoryHolder(view)
    }

    override fun onBindViewHolder(holder: categoryHolder, position: Int) {
        val category_item=categorydataClass.get(position)
        holder.imageview.setImageResource(category_item.image_category_DC)
        holder.textview.text=category_item.name_category_DC
    }

    override fun getItemCount(): Int {
       return categorydataClass.size?:0
    }

    class categoryHolder(val itemView:View):ViewHolder(itemView){
        val imageview:ShapeableImageView
        val textview:TextView

        init {
            imageview=itemView.findViewById(R.id.single_item)
            textview=itemView.findViewById(R.id.category_nametxt)
        }
    }
}