package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.databinding.SingleFashionitemOfRvBinding
//import com.example.myapplication.databinding.SingleFashionitemOfRvBinding

class fashionItemsAdapter(var fashionData:List<DataItem3?>?):Adapter<fashionItemsAdapter.fashionItemsHolder>() {
    var onImgClickListener: OnImgClickListener?=null

    fun upDateData(DataIms:List<DataItem3?>?){
        fashionData=DataIms
        notifyDataSetChanged()
    }

    class fashionItemsHolder(val bindings: SingleFashionitemOfRvBinding):ViewHolder(bindings.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): fashionItemsHolder {
        val bindings=SingleFashionitemOfRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return fashionItemsHolder(bindings)
    }

    override fun onBindViewHolder(holder: fashionItemsHolder, position: Int) {
        val currentfashionData=fashionData?.get(position)
       // Glide.with(holder.itemView).load(currentfashionData?.image).into(holder.bindings.detailedFashionImgs)

        with(holder){
            with(bindings){
                detailedFashionName.text=currentfashionData?.slug
                detailedFashionImgs.setOnClickListener(object :OnClickListener{
                    override fun onClick(v: View?) {
                        onImgClickListener?.onImgClicked()
                    }
                })
               // detailedFashionImgs.setImageResource(R.drawable.mbags)
            }
        }

    }

    override fun getItemCount(): Int {
        return fashionData?.size?:0
    }
    interface OnImgClickListener{
        fun onImgClicked()
    }


}