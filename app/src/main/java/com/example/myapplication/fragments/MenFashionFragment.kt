package com.example.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.*
import com.google.android.material.imageview.ShapeableImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MenFashionFragment private constructor(): Fragment() {
    companion object{
        var subCategories_ID:String?=null
        var SubCategory_Data:DataItem?=null
        //var SubCategory_Data:DataItem3?=null

        fun CreateInstance(subcategories_Data:DataItem):MenFashionFragment{
            subCategories_ID=subcategories_Data.id
            //subCategories_ID=subcategories_Data.category
            SubCategory_Data=subcategories_Data
           // SubCategory_Data=subcategories_Data
            return MenFashionFragment()
        }
    }
    lateinit var categoryFashion:TextView
    lateinit var headerImg:ShapeableImageView
    lateinit var categoryFashiontxt:TextView

    lateinit var fashionItem_RecyclerView: RecyclerView
    lateinit var fashionitemAdapter:fashionItemsAdapter
    lateinit var fashionDataArr:ArrayList<FashionData>

    lateinit var retroSubCategories:Retrofit
    lateinit var IntImplement_SubCategories:ApiInterface
    lateinit var call_subCAtegories:Call<SubCategoriesResponse>
    lateinit var CategoryID:String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_men_fashion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fashionItem_RecyclerView=view.findViewById(R.id.detailed_fashion_items_rv)

        categoryFashion=view.findViewById(R.id.category_fashion)
        headerImg=view.findViewById(R.id.header_image)
        categoryFashiontxt=view.findViewById(R.id.catg_fashion_txt)

        categoryFashion.text= SubCategory_Data?.slug
        categoryFashiontxt.text=SubCategory_Data?.slug

        Glide.with(requireContext()).load(SubCategory_Data?.image).into(headerImg)

        initFashionData()
        fashionitemAdapter=fashionItemsAdapter(null)
        fashionItem_RecyclerView.adapter=fashionitemAdapter
        getRetroStart()

        fashionitemAdapter.onImgClickListener=object :fashionItemsAdapter.OnImgClickListener{
            override fun onImgClicked() {
                val intent= Intent(requireContext(),
                    ChosenCategItemsActivity.createInstance(CategoryID!!)::class.java)
                startActivity(intent)
            }
        }
    }

    fun getRetroStart(){
        retroSubCategories=Retrofit.Builder().baseUrl("https://route-ecommerce.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        IntImplement_SubCategories=retroSubCategories.create(ApiInterface::class.java)
        call_subCAtegories=IntImplement_SubCategories.getSubCategoriesFromCategory(subCategories_ID!!)
        call_subCAtegories.enqueue(object :Callback<SubCategoriesResponse>{
            override fun onResponse(
                call: Call<SubCategoriesResponse>,
                response: Response<SubCategoriesResponse>
            ) {
                val DataItems=response.body()?.data
                if(response.body()?.data?.size!!>=1){
                    CategoryID=response.body()?.data?.get(1)?.category!!
                    //response.body().data.get(1)
                }

                fashionitemAdapter.upDateData(DataItems)
                Toast.makeText(requireContext(),"Succeed",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<SubCategoriesResponse>, t: Throwable) {
                Toast.makeText(requireContext(),"Failed",Toast.LENGTH_LONG).show()
            }

        })
    }

    fun initFashionData(){
        fashionDataArr= ArrayList()
        fashionDataArr.add(FashionData(R.drawable.tshirt_m,"T-shirts"))
        fashionDataArr.add(FashionData(R.drawable.mshorts,"Shorts"))
        fashionDataArr.add(FashionData(R.drawable.mfootwear,"Footwear"))
        fashionDataArr.add(FashionData(R.drawable.msuits,"Suits"))
        fashionDataArr.add(FashionData(R.drawable.mwatches,"Watches"))
        fashionDataArr.add(FashionData(R.drawable.mbags,"Bags"))
        fashionDataArr.add(FashionData(R.drawable.mfashion2,"New arrival"))
    }

}