package com.example.myapplication.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.myapplication.R


class HomeFragment : Fragment() {
    lateinit var imageSlider: ImageSlider
    lateinit var images:ArrayList<SlideModel>
    lateinit var recyclerview:RecyclerView
    lateinit var category_adapter:categoryAdapter
    lateinit var category_data:ArrayList<categoryDataClass>

    lateinit var recyclerview_homeAppliance:RecyclerView
    lateinit var homeAppliance_adapter:homeApplianceAdapter
    lateinit var HomeApplianceCategory_data:ArrayList<Int>





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageSlider= view.findViewById(R.id.image_slider)
        initSliderDAta()
        imageSlider.setImageList(images)
        recyclerview=view.findViewById(R.id.recycler_view)
        initCategoryData()
        category_adapter=categoryAdapter(category_data)
        recyclerview.adapter=category_adapter

        recyclerview_homeAppliance=view.findViewById(R.id.homeAppliance_recyclerView)
        initHomeApplianceData()
        homeAppliance_adapter=homeApplianceAdapter(HomeApplianceCategory_data)
        recyclerview_homeAppliance.adapter=homeAppliance_adapter


    }

    fun initSliderDAta(){
        images= ArrayList()
        images.add(SlideModel(R.drawable.headphones_3))
        images.add(SlideModel(R.drawable.zara))
        images.add(SlideModel(R.drawable.mac))
    }

    fun initCategoryData(){
        category_data= ArrayList()
        category_data.add(categoryDataClass(R.drawable.women_fashion,"Women's Fashion"))
        category_data.add(categoryDataClass(R.drawable.beauty,"Beauty"))
        category_data.add(categoryDataClass(R.drawable.men_fashion,"men's Fashion"))
        category_data.add(categoryDataClass(R.drawable.headphones_2,"Headphones"))
        category_data.add(categoryDataClass(R.drawable.electronics,"Electronics"))
        category_data.add(categoryDataClass(R.drawable.skin_care,"Skin Care"))
        category_data.add(categoryDataClass(R.drawable.baby_careseat,"Baby's supplement"))
        category_data.add(categoryDataClass(R.drawable.camera,"Camera"))
    }

    fun initHomeApplianceData(){
        HomeApplianceCategory_data=ArrayList()
        HomeApplianceCategory_data.add(R.drawable.washing_machine)
        HomeApplianceCategory_data.add(R.drawable.cooker_3)
        HomeApplianceCategory_data.add(R.drawable.iron)
    }

}