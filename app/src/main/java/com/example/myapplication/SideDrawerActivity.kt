package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.cart.CartActivity
import com.example.myapplication.fragments.MenFashionFragment
//import com.example.myapplication.databinding.ActivitySideDrawerBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SideDrawerActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var drawer_layout:DrawerLayout
    lateinit var getSideDrawer:ImageView
    lateinit var navview:NavigationView
    lateinit var fashion_framLayout: FrameLayout
    lateinit var MenFragment:MenFashionFragment
    lateinit var openCourt:ImageView


    lateinit var retroGetCategories:Retrofit
    lateinit var IntImplement_GetCategories:ApiInterface
    lateinit var call_GetCAtegories:Call<AllCategoriesResponse>
    var DataItms:List<DataItem?>?=null
    //var DataItms3:List<DataItem3>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side_drawer)

        drawer_layout=findViewById(R.id.drawer_layout)
        getSideDrawer=findViewById(R.id.menu_get_sidedrawer)

        openCourt=findViewById(R.id.open_cartActivity)
        openCourt.setOnClickListener({
            val intent=Intent(this,CartActivity::class.java)
            startActivity(intent)
        })

        getSideDrawer.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                drawer_layout.open()
            }
        })

        getDataFromRetro()
        navview=findViewById(R.id.nav_view)

        FashionItem_clickListener()

    }

    fun getDataFromRetro(){
        retroGetCategories= Retrofit.Builder().baseUrl("https://route-ecommerce.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        IntImplement_GetCategories=retroGetCategories.create(ApiInterface::class.java)
        call_GetCAtegories=IntImplement_GetCategories.getAllCAtegories()
        call_GetCAtegories.enqueue(object : Callback<AllCategoriesResponse> {
            override fun onResponse(
                call: Call<AllCategoriesResponse>,
                response: Response<AllCategoriesResponse>
            ) {
                DataItms=response?.body()?.data!!
                val womenfashion=DataItms?.get(2)
                val womenFragment=MenFashionFragment.CreateInstance(womenfashion!!)
                pushFragment(womenFragment)
                Toast.makeText(baseContext,"Succeed",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<AllCategoriesResponse>, t: Throwable) {
                Toast.makeText(baseContext,"FAiled",Toast.LENGTH_LONG).show()
            }

        })
    }


    fun FashionItem_clickListener(){
        navview.setNavigationItemSelectedListener {
            if(it.itemId==R.id.men_fashion){
                prepareSubCAtegory(1)
                pushFragment(MenFragment)
            }else if(it.itemId==R.id.music){
                prepareSubCAtegory(0)
                pushFragment(MenFragment)
            }else if(it.itemId==R.id.women_fashion){
                prepareSubCAtegory(2)
                pushFragment(MenFragment)
            }else if(it.itemId==R.id.super_market){
                prepareSubCAtegory(3)
                pushFragment(MenFragment)
            }else if(it.itemId==R.id.baby_toys){
                prepareSubCAtegory(4)
                pushFragment(MenFragment)
            }else if(it.itemId==R.id.home){
                prepareSubCAtegory(5)
                pushFragment(MenFragment)
            }else if(it.itemId==R.id.books){
                prepareSubCAtegory(6)
                pushFragment(MenFragment)
            }else if(it.itemId==R.id.beauty_and_health){
                prepareSubCAtegory(7)
                pushFragment(MenFragment)
            }else if(it.itemId==R.id.mobiles){
                prepareSubCAtegory(8)
                pushFragment(MenFragment)
            }else if(it.itemId==R.id.laptop_electronics){
                prepareSubCAtegory(9)
                pushFragment(MenFragment)
            }
            return@setNavigationItemSelectedListener true
        }

    }

    fun prepareSubCAtegory(index:Int){
        val SubCategoryID=DataItms?.get(index)
        //val subcate=DataItms3?.get(index)
       MenFragment= MenFashionFragment.CreateInstance(SubCategoryID!!)
        //MenFragment= MenFashionFragment.CreateInstance(subcate!!)
    }


    fun pushFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.itemFAshion_FramLayout,fragment)
            .commit()
        drawer_layout.close()
    }


}