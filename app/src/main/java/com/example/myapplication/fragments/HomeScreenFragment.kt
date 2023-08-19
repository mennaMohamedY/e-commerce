package com.example.myapplication.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.myapplication.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeScreenFragment : Fragment() {
    lateinit var imageSlider: ImageSlider
    lateinit var images:ArrayList<SlideModel>
    lateinit var recyclerview:RecyclerView
    lateinit var category_adapter:categoryAdapter
    lateinit var category_data:ArrayList<categoryDataClass>

    lateinit var recyclerview_homeAppliance:RecyclerView
    lateinit var homeAppliance_adapter:homeApplianceAdapter
    lateinit var HomeApplianceCategory_data:ArrayList<Int>

    lateinit var retrofit: Retrofit
    lateinit var call:Call<AllCategoriesResponse>
    lateinit var interfaceImpl:ApiInterface
    lateinit var categoryCall:Call<ChosenCategoryResponse>
    var Image:String="123"
    var Name:String="234"
    var PublishedAt:String="123"
    lateinit var  d: Drawable
    lateinit var imagePath:String

    //var onCategoryCLickListener: categoryAdapter.OnCategoryCLickListener?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageSlider= view.findViewById(R.id.image_slider)
        imagePath="https://media.product.which.co.uk/prod/images/900_450/gm-453a27c8-91c5-4b0b-80a4-6702a58cd0cf-android-main.jpeg"
        initSliderDAta()
        imageSlider.setImageList(images)
        recyclerview=view.findViewById(R.id.recycler_view)
       // initCategoryData()
        category_adapter=categoryAdapter(null)
        recyclerview.adapter=category_adapter



        Glide.with(this)
            .asBitmap()
            .load(imagePath)
            .into(object : CustomTarget<Bitmap>(){
                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                }

                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                     d = BitmapDrawable(resources, resource)
                }
            })

        recyclerview_homeAppliance=view.findViewById(R.id.homeAppliance_recyclerView)
        initHomeApplianceData()
        homeAppliance_adapter=homeApplianceAdapter(HomeApplianceCategory_data)
        recyclerview_homeAppliance.adapter=homeAppliance_adapter


        retrofit=Retrofit.Builder().baseUrl("https://route-ecommerce.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        interfaceImpl=retrofit.create(ApiInterface::class.java)
        call=interfaceImpl.getAllCAtegories()
        call.enqueue(object :Callback<AllCategoriesResponse> {
            override fun onResponse(
                call: Call<AllCategoriesResponse>,
                response: Response<AllCategoriesResponse>
            ) {
                val CategoriesData = response.body()?.data
                category_adapter.UpDataData(CategoriesData)
                Toast.makeText(requireContext(), "success", Toast.LENGTH_LONG).show()
                category_adapter.onCategoryCLickListener=object :categoryAdapter.OnCategoryCLickListener{
                    override fun onCategoryClicked(categoryItem: DataItem?, position: Int) {
                        val intent =Intent(requireContext(),ChosenCategory.getInstance(categoryItem!!,position)::class.java)
                        Toast.makeText(requireContext(),"position chosen is ${position}",Toast.LENGTH_LONG).show()
                        startActivity(intent)
                    }
                }}
            override fun onFailure(call: Call<AllCategoriesResponse>, t: Throwable) {
                Toast.makeText(requireContext(),"Failed",Toast.LENGTH_LONG).show()
                Toast.makeText(requireContext(),t.cause.toString(),Toast.LENGTH_LONG).show()
                Log.e("Error Retrofit",t.message.toString())

            }})

               /* category_adapter.onCategoryCLickListener =
                    object : categoryAdapter.OnCategoryCLickListener {
                        override fun onCategoryClicked(categoryItem: DataItem?) {
                            onCategoryCLickListener?.onCategoryClicked(categoryItem)
                            val intent = Intent(requireContext(),ChosenCategory.getInstance(categoryItem!!)::class.java)
                        }
                    }}
            override fun onFailure(call: Call<AllCategoriesResponse>, t: Throwable) {

            }})

                */

            /*object :categoryAdapter.OnCategoryCLickListener{
                override fun onCategoryClicked() {
                    val intent=Intent(requireContext(),ChosenCategory::class.java)
                    intent.putExtra(Constants.id_Key,id)

                    /*categoryCall=interfaceImpl.getSpecificCAtegory(id)

                    categoryCall.enqueue(object :Callback<ChosenCategoryResponse>{
                        override fun onResponse(
                            call: Call<ChosenCategoryResponse>,
                            response: Response<ChosenCategoryResponse>
                        ) {
                            Image=response.body()?.data?.image!!
                            Name=response.body()?.data?.name!!
                            PublishedAt=response.body()?.data?.updatedAt!!
                            Toast.makeText(requireContext(),"succeeded",Toast.LENGTH_LONG).show()
                        }

                        override fun onFailure(
                            call: Call<ChosenCategoryResponse>,
                            t: Throwable
                        ) {
                            Toast.makeText(requireContext(),"failed",Toast.LENGTH_LONG).show()
                        }

                    })*/
                    intent.putExtra(Constants.image_Key,Image)
                    intent.putExtra(Constants.name_Key,Name)
                    intent.putExtra(Constants.publishedAt_Key,PublishedAt)
                    startActivity(intent)

                }
            }
        }
        override fun onFailure(call: Call<AllCategoriesResponse>, t: Throwable) {
            Toast.makeText(requireContext(),"Failed",Toast.LENGTH_LONG).show()
        }
    })

                 */


            }



            fun initSliderDAta() {
                images = ArrayList()
                images.add(SlideModel(R.drawable.headphones_3))
                images.add(SlideModel(R.drawable.zara))
                images.add(SlideModel(R.drawable.mac))
                images.add(SlideModel(imagePath))

            }

            fun initCategoryData() {
                category_data = ArrayList()
                category_data.add(categoryDataClass(R.drawable.women_fashion, "Women's Fashion"))
                category_data.add(categoryDataClass(R.drawable.beauty, "Beauty"))
                category_data.add(categoryDataClass(R.drawable.men_fashion, "men's Fashion"))
                category_data.add(categoryDataClass(R.drawable.headphones_2, "Headphones"))
                category_data.add(categoryDataClass(R.drawable.electronics, "Electronics"))
                category_data.add(categoryDataClass(R.drawable.skin_care, "Skin Care"))
                category_data.add(categoryDataClass(R.drawable.baby_careseat, "Baby's supplement"))
                category_data.add(categoryDataClass(R.drawable.camera, "Camera"))
            }

            fun initHomeApplianceData() {
                HomeApplianceCategory_data = ArrayList()
                HomeApplianceCategory_data.add(R.drawable.washing_machine)
                HomeApplianceCategory_data.add(R.drawable.cooker_3)
                HomeApplianceCategory_data.add(R.drawable.iron)
            }


        }