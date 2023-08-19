package com.example.myapplication.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myapplication.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoriesFragment : Fragment() {

    companion object{
        var categoryPassed:List<DataItem>?=null
        fun creatInstance(CategoryPassed:List<DataItem>):CategoriesFragment{
            categoryPassed=CategoryPassed
            return CategoriesFragment()
        }
    }

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var drawer_layout: DrawerLayout
    lateinit var getSideDrawer: ImageView
    lateinit var navView:NavigationView
    lateinit var fashion_framLayout:FrameLayout
    lateinit var retr2:Retrofit
    lateinit var retCall:Call<AllCategoriesResponse>
    lateinit var IntImpl:ApiInterface

    lateinit var subMenu: SubMenu






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //hh()
        return inflater.inflate(R.layout.fragment_categories, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navView=view.findViewById(R.id.nav_view)
        //var mnu=navView.menu
        //subMenu=mnu.addSubMenu("New Super SubMenu")
        //hh()
        //navView.menu.clear()
        //navView.inflateMenu(R.menu.activity_main_drawer)
       /*
        val menu =navView.menu
        val submenu=menu.addSubMenu("Custom memory")
        submenu.add(0,0,0,"menu1",)
        submenu.add(0,1,0,"menu2",)
        submenu.add(0,2,0,"menu3",)
        submenu.add(0,3,0,"menu4",)
        //navView.menu.add("one")
        //navView.menu.add("two")
        //navView.menu.add("three")
        navView.invalidate()

        */
        drawer_layout=view.findViewById(R.id.drawer_layout)
        getSideDrawer=view.findViewById(R.id.menu_get_sidedrawer)
        getSideDrawer.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                //navView.invalidate()
                drawer_layout.open()

            }
        })

    }
    private fun setMenuItemActionView(menuItem: MenuItem){
        menuItem.setActionView(R.layout.fragment_categories)
    }

    fun hh(){
        subMenu.add("one")
        subMenu.add("two")
        subMenu.add("three")
        navView.invalidate()
    }
    fun openR(){
        retr2=Retrofit.Builder()
            .baseUrl("https://route-ecommerce.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()



        IntImpl=retr2.create(ApiInterface::class.java)
        retCall=IntImpl.getAllCAtegories()
        retCall.enqueue(object :Callback<AllCategoriesResponse>{
            override fun onResponse(
                call: Call<AllCategoriesResponse>,
                response: Response<AllCategoriesResponse>
            ) {
                val categItem=response.body()?.data
                /*

                categItem?.forEach { dataItem->
                    //navView.menu.add()
                    Toast.makeText(requireContext(),"Succeed",Toast.LENGTH_LONG).show()
                }

                 */
                for(i in 0..categItem?.size!!){
                    val categ=categItem.get(i)
                    //navView.menu.add(i,i,i+1,"categ?.name")
                    navView.menu.add("helloo")
                    Toast.makeText(requireContext(),"Succeed",Toast.LENGTH_LONG).show()


                }
            }
            override fun onFailure(call: Call<AllCategoriesResponse>, t: Throwable) {
                Toast.makeText(requireContext(),"Failed",Toast.LENGTH_LONG).show()
            }
        })



    }








}