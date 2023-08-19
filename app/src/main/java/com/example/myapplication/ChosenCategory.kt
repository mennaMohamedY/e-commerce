package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.cart.CartActivity
import com.example.myapplication.fragments.HomeScreenFragment
import com.example.myapplication.fragments.MenFashionFragment
import com.example.myapplication.databinding.ActivityChosenCategoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChosenCategory : AppCompatActivity() {
    lateinit var bindingChoCateg:ActivityChosenCategoryBinding
    lateinit var retro: Retrofit
    lateinit var ca: Call<AllCategoriesResponse>
    lateinit var intImp:ApiInterface
    lateinit var categoriesFragment:HomeScreenFragment
    lateinit var MenFragment:MenFashionFragment


    var DataItms_chosenCateg:List<DataItem?>?=null


    companion object{
        var CAtegoryItem:DataItem?=null
        var POsition:Int?=null
        fun getInstance(categoryItem:DataItem,position:Int):ChosenCategory{
            CAtegoryItem=categoryItem
            POsition=position
            return ChosenCategory()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingChoCateg = ActivityChosenCategoryBinding.inflate(layoutInflater)
        val view = bindingChoCateg.root
        setContentView(view)

        val intent = Intent()
        val id = intent.getStringExtra(Constants.id_Key)
        bindingChoCateg.FramLayoutHomeFrag
        getDataFromRetro()
        bindingChoCateg.backArrowHomeFrag.setOnClickListener({
            finish()
        })
        bindingChoCateg.goToCourtHomeFrag.setOnClickListener({
            val intent=Intent(this,CartActivity::class.java)
            startActivity(intent)
        })
    }
        fun getDataFromRetro() {
            retro = Retrofit.Builder().baseUrl("https://route-ecommerce.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()
            intImp = retro.create(ApiInterface::class.java)
            ca = intImp.getAllCAtegories()
            ca.enqueue(object : Callback<AllCategoriesResponse> {
                override fun onResponse(
                    call: Call<AllCategoriesResponse>,
                    response: Response<AllCategoriesResponse>
                ) {
                    DataItms_chosenCateg=response.body()?.data
                    prepareSubCAtegory(POsition!!)
                    Toast.makeText(baseContext, "Succeed", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<AllCategoriesResponse>, t: Throwable) {
                    Toast.makeText(baseContext, "FAiled", Toast.LENGTH_LONG).show()
                }

            })
        }

    fun prepareSubCAtegory(index:Int){
        val SubCategoryID=DataItms_chosenCateg?.get(index)
        //val subcate=DataItms3?.get(index)
        MenFragment= MenFashionFragment.CreateInstance(SubCategoryID!!)
        pushFragment(MenFragment)
        //MenFragment= MenFashionFragment.CreateInstance(subcate!!)
    }


    fun pushFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.FramLayout_HomeFrag,fragment)
            .commit()
    }






    /*
    categoriesFragment.onCategoryCLickListener=object :categoryAdapter.OnCategoryCLickListener{
        override fun onCategoryClicked(categoryItem: DataItem?) {


            //ca=intImp.getSpecificCategory(id)

            ca=intImp.getSpecificCAtegory(categoryItem?.id)
            ca?.enqueue(object :Callback<ChosenCategoryResponse?>{
                override fun onResponse(
                    call: Call<ChosenCategoryResponse?>,
                    response: Response<ChosenCategoryResponse?>
                ) {
                    val ChosenCategoryItem=response.body()?.data
                    bindingChoCateg.categoriesTxt.text=ChosenCategoryItem?.slug
                    bindingChoCateg.publishedAt.text=ChosenCategoryItem?.createdAt

                    Glide.with(this@ChosenCategory).load(ChosenCategoryItem?.image).into(bindingChoCateg.imageSlider)
                    Toast.makeText(this@ChosenCategory,"success",Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<ChosenCategoryResponse?>, t: Throwable) {
                    Toast.makeText(this@ChosenCategory,"FAiled",Toast.LENGTH_LONG).show()
                }


        })

    }}

     */

        /*
        retro=Retrofit.Builder().baseUrl("https://route-ecommerce.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        intImp=retro.create(ApiInterface::class.java)
        //ca=intImp.getSpecificCategory(id)

        ca=intImp.getSpecificCAtegory(id)
        ca?.enqueue(object :Callback<ChosenCategoryResponse?>{
            override fun onResponse(
                call: Call<ChosenCategoryResponse?>,
                response: Response<ChosenCategoryResponse?>
            ) {
                val ChosenCategoryItem=response.body()?.data
                bindingChoCateg.categoriesTxt.text=ChosenCategoryItem?.slug
                bindingChoCateg.publishedAt.text=ChosenCategoryItem?.createdAt

                Glide.with(this@ChosenCategory).load(ChosenCategoryItem?.image).into(bindingChoCateg.imageSlider)
                Toast.makeText(this@ChosenCategory,"success",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<ChosenCategoryResponse?>, t: Throwable) {
                Toast.makeText(this@ChosenCategory,"FAiled",Toast.LENGTH_LONG).show()
            }

        })
        */
        /*
        val img=intent.getStringExtra(Constants.image_Key)
        val name=intent.getStringExtra(Constants.name_Key)
        val publishedat=intent.getStringExtra(Constants.publishedAt_Key)
        bindingChoCateg.categoriesTxt.text=name
        bindingChoCateg.publishedAt.text=publishedat
        Glide.with(this).load(img).into(bindingChoCateg.imageSlider)

         */










}