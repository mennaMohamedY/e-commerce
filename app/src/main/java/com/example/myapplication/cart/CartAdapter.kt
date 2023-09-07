package com.example.myapplication.cart

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.room.FavouriteItems
import com.google.android.material.imageview.ShapeableImageView

//class CartAdapter(var dataItem2:ArrayList<DataItem2?>?):Adapter<CartAdapter.MyCartAdapter>() {
//class CartAdapter(val dat_Item2:ArrayList<CartDataClass>):Adapter<CartAdapter.MyCartAdapter>() {
class CartAdapter(var dat_Item2:List<FavouriteItems?>?):Adapter<CartAdapter.MyCartAdapter>() {

   var onDeleteClickListener:OnDeleteClickListener?=null
   fun UpdateData(dataItem2: List<FavouriteItems?>?){
        if(this.dat_Item2 != dataItem2){
            this.dat_Item2=dataItem2
            notifyDataSetChanged()
        }
    }
    fun updateDataOnRemove(position: Int){
            notifyItemRemoved(position)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartAdapter {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.single_cort_item,parent,false)
        return MyCartAdapter(view)
    }

    /*
    override fun onBindViewHolder(holder: MyCartAdapter, position: Int) {
        var currentItem=dat_Item2?.get(position)
//        Glide.with(holder.itemView).load(currentItem?.imageCover).into(holder.img!!)
        holder.productName?.setText(currentItem?.p_Name)
        holder.price?.setText(currentItem?.p_Price)
        holder.img?.setImageResource(currentItem!!.Img)
        holder.color?.setBackgroundColor(currentItem!!.p_color)
    }
     */
    override fun onBindViewHolder(holder: MyCartAdapter, position: Int) {
        var currentItem=dat_Item2?.get(position)
        holder.productName?.setText(currentItem?.prod_name)
        holder.price?.setText("EGP"+currentItem?.prod_Price!!)
        holder.imgg?.let { Glide.with(holder.itemView).load(currentItem?.img).into(it) }
        //Glide.with(holder.itemView).load(currentItem?.img).into(holder.imgg!!)
        //holder.img.setImageResource(Glide.with(holder.itemView).load(dat_Item2.get(0).img).)
        //val ig ="https://res.cloudinary.com/dwp0imlbj/image/upload/v1680747343/Route-Academy-categories/1681511179514.png"
        //Glide.with(holder.itemView).load(ig).into(holder.imgg!!)
       //holder.color?.setBackgroundColor(currentItem!!.prod_color!!)
        holder.color?.setImageResource(currentItem?.prod_color?:R.color.transparent)
        holder.colorName_size?.text=currentItem?.prod_colorName?:"no color was chosen"
       /*
        holder.constrain_court_col?.background?.setColorFilter(
            holder.constrain_court_col?.context!!.getColor(R.color.pink),PorterDuff.Mode.SRC_ATOP
        )

        */


        //add_TheImg?.addImg(position,holder.imgg!!)
        holder.deletFromFav?.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                onDeleteClickListener?.onDelete(holder.adapterPosition,currentItem!!)
            }
        })

    }

    interface OnDeleteClickListener{
        fun onDelete(position: Int,fav_item:FavouriteItems)
    }



    override fun getItemCount(): Int {
        return dat_Item2?.size?:0
    }

    class MyCartAdapter(itemView:View):ViewHolder(itemView){
        //var img:ShapeableImageView?=null
        var imgg:ShapeableImageView?=null
        var productName:TextView?=null
        var color:ImageView?=null
        var colorName_size:TextView?=null
        var price:TextView?=null
        var recyclerBin:ImageView?=null
        var deletFromFav:ImageView?=null
        init {
            imgg=itemView.findViewById(R.id.shapeableImg_court)
            productName=itemView.findViewById(R.id.product_nameCourt)
            color=itemView.findViewById(R.id.color_col)
            colorName_size=itemView.findViewById(R.id.size_color_txt_cort)
            price=itemView.findViewById(R.id.price)
            recyclerBin=itemView.findViewById(R.id.todelete_icon)
            deletFromFav=itemView.findViewById(R.id.todelete_icon)
        }

    }
}

