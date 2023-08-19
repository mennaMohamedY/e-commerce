package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ChoosenCategItemsBinding

class chosenCategAdapter(var choosenCategData:List<DataItem2?>?,var subCategoryID:String) :Adapter<chosenCategAdapter.MyChosenCategHolder>(){

    class MyChosenCategHolder(val bindings:ChoosenCategItemsBinding):ViewHolder(bindings.root)

    lateinit var  DataArray:ArrayList<DataItem2?>
    //for the favourite double click sack
    var count=1

    fun updateData(CategData:List<DataItem2?>){ //,SubCategory_ID:String
        choosenCategData=CategData
        //subCategoryID=SubCategory_ID
        notifyDataSetChanged()
    }


    var onItemClickListener:OnItemClickListener?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyChosenCategHolder {
        val bindings=ChoosenCategItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MyChosenCategHolder(bindings)
    }



    override fun onBindViewHolder(holder: MyChosenCategHolder, position: Int) {

       //var arrayCreated_size= arrayRefillment(subCategoryID,position)
        //DataArray.size-1

        if(position<=DataArray.size-1){
            val currentItem=DataArray.get(position)
            Glide.with(holder.itemView).load(currentItem?.imageCover).into(holder.bindings.hoimg)
            with(holder){
                with(bindings){
                    itemName.text=currentItem?.slug
                    itemDescription.text=currentItem?.description
                    itemPrice.text=currentItem?.price.toString()
                    itemRate.text=currentItem?.ratingsQuantity.toString()

                    hoimg.setOnClickListener(object :OnClickListener{
                        override fun onClick(v: View?) {
                            onItemClickListener?.onItemClicked(holder.adapterPosition,currentItem)
                        }
                    })
                    addBtn.setOnClickListener({
                        onItemClickListener?.onItemClicked(holder.adapterPosition,currentItem)
                    })

                }}
        }

        holder.bindings.favouriteInSubCategories.setOnClickListener({
            if(count%2 != 0){
                holder.bindings.favouriteInSubCategories.setImageResource(R.drawable.colored_favourit)
                count+=1
            }else{
                holder.bindings.favouriteInSubCategories.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                count+=1
            }
        })


       // for(i in 0..DataArray.size-1){ }


        //for(i in 0..DataArray.size-2){}

            /*while (true){
                if(position>=DataArray.size){
                    break
                }else{
                    val currentItem=DataArray.get(position)
                    Glide.with(holder.itemView).load(currentItem?.imageCover).into(holder.bindings.hoimg)
                    with(holder){
                        with(bindings){
                            itemName.text=currentItem?.slug
                            itemDescription.text=currentItem?.description
                            itemPrice.text=currentItem?.price.toString()
                            itemRate.text=currentItem?.ratingsQuantity.toString()

                            hoimg.setOnClickListener(object :OnClickListener{
                                override fun onClick(v: View?) {
                                    onItemClickListener?.onItemClicked(holder.adapterPosition,currentItem)
                                }
                            })
                            addBtn.setOnClickListener({
                                onItemClickListener?.onItemClicked(holder.adapterPosition,currentItem)
                            })

                        }
                    }


                }


             */

            }






        /*
        while (true){
            if(position>=DataArray.size-1){
                break
        }else{
                val currentItem=DataArray.get(position)
                Glide.with(holder.itemView).load(currentItem?.imageCover).into(holder.bindings.hoimg)
                with(holder){
                    with(bindings){
                        itemName.text=currentItem?.slug
                        itemDescription.text=currentItem?.description
                        itemPrice.text=currentItem?.price.toString()
                        itemRate.text=currentItem?.ratingsQuantity.toString()

                        hoimg.setOnClickListener(object :OnClickListener{
                            override fun onClick(v: View?) {
                                onItemClickListener?.onItemClicked(holder.adapterPosition,currentItem)
                            }
                        })
                        addBtn.setOnClickListener({
                            onItemClickListener?.onItemClicked(holder.adapterPosition,currentItem)
                        })

                    }
                }
                continue
            }

         */
        //val currentItem=choosenCategData?.get(position)


    //to compare each subCategory_ID with dataItem from DAtaItem2 and fill empty array with
    //only the dataItems related to this subCategory

    fun arrayRefillment(subCategory_id: String,position: Int):Int{

        var currentItem=choosenCategData?.get(position)
        //if(subCategory_id==currentItem?.subcategory?.get(0)?.category)
        if (subCategory_id==currentItem?.category?.id){
            //DataArray.add(j,currentItem)
            DataArray.add(currentItem)
        }else{
            DataArray.add(null)
        }
        return DataArray.size
    }


        //var j=0
    /*
        for(i in 0..choosenCategData!!.size-2){
            if (subCategory_id==currentItem?.subcategory?.get(0)?.category){
                //DataArray.add(j,currentItem)
                DataArray.add(currentItem)
                //j++
            }
        }
        if(DataArray.size==0){
            DataArray.add(choosenCategData?.get(0)!!)
        }
        return DataArray.size
    }

     */





    override fun getItemCount(): Int {
        //var sizee=choosenCategData?.size
        DataArray=ArrayList()
        for(i in 0..39){
            var currentItem=choosenCategData?.get(i)
            //if(subCategoryID==currentItem?.subcategory?.get(0)?.category)
            if (subCategoryID==currentItem?.category?.id){
                DataArray.add(currentItem)
            }
        }
        return DataArray?.size?:0
        //return choosenCategData?.size?:0
       // DataArray=ArrayList()
        //return DataArray.size
       // return 5
    }

    interface OnItemClickListener{
        fun onItemClicked(position: Int,item:DataItem2? )
    }




    /*class MyChosenCategHolder(val itemView:View):ViewHolder(itemView){
        val itemImg:ShapeableImageView
        val itemName:TextView
        val itemDescrip:TextView
        val itemPrice:TextView
        val itemRate:TextView
        val addBtn:AppCompatButton
        init {
            itemImg=itemView.findViewById(R.id.hoimg)
            itemName=itemView.findViewById(R.id.item_name)
            itemDescrip=itemView.findViewById(R.id.item_description)
            itemPrice=itemView.findViewById(R.id.item_price)
            itemRate=itemView.findViewById(R.id.item_rate)
            addBtn=itemView.findViewById(R.id.addBtn)

        }
    }

     */
}