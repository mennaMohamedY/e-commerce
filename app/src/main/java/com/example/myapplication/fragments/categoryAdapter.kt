package com.example.myapplication.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.myapplication.DataItem
import com.example.myapplication.databinding.SingleCategoryItemRvBinding

class categoryAdapter(var categorydataClass:List<DataItem?>?):Adapter<categoryAdapter.categoryHolder>()  {

    var onCategoryCLickListener: OnCategoryCLickListener?=null
    fun UpDataData(categories:List<DataItem?>?){
        this.categorydataClass=categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryHolder {
        val binding=SingleCategoryItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return categoryHolder(binding)

    }

    override fun onBindViewHolder(holder: categoryHolder, position: Int) {
        val arrayList:ArrayList<Int> = arrayListOf()
        val category_item=categorydataClass?.get(position)
       // holder.imageview.setImageResource(category_item.image_category_DC)
        Glide.with(holder.itemView).load(category_item?.image).into(holder.binding.singleItem)


        with(holder){
            with(binding){
                categoryNametxt.text=category_item?.slug
               // singleItem.setImageResource(R.drawable.mbags)
                itemView.setOnClickListener({
                    onCategoryCLickListener?.onCategoryClicked(category_item,position)
                })
            }
        }

    }
    interface OnCategoryCLickListener{
        fun onCategoryClicked(categoryItem:DataItem?,position: Int)
    }



    override fun getItemCount(): Int {
       return categorydataClass?.size?:0
    }

    class categoryHolder(val binding:SingleCategoryItemRvBinding):ViewHolder(binding.root)

}