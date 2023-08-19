package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.cart.CartActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChosenCategItemsActivity : AppCompatActivity() {

    companion  object{
        var SubCategoryID:String?=null
        fun createInstance(subCategoryId:String):ChosenCategItemsActivity{
            SubCategoryID=subCategoryId
            return ChosenCategItemsActivity()
        }
    }

    lateinit var ItemsRecyclerView:RecyclerView
    lateinit var ItemsData:ArrayList<choosen_categItem_data>
    lateinit var ItemsAdapter:chosenCategAdapter

    lateinit var retrofie:Retrofit
    lateinit var apiIm:ApiInterface
    lateinit var retroieCall:Call<AllProductsResponse>

    lateinit var back:ImageView
    lateinit var court:ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chosen_categ_items)
        ItemsRecyclerView = findViewById(R.id.chosenCategItems_rv)
        back=findViewById(R.id.back_arrow)
        court=findViewById(R.id.go_toCourt)
        //InitItemData()
        ItemsAdapter = chosenCategAdapter(null, subCategoryID = SubCategoryID!!)
        ItemsRecyclerView.adapter = ItemsAdapter
        getRetroReady()

        back.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                finish()
            }
        })

        court.setOnClickListener({
            val intent=Intent(this,CartActivity::class.java)
            startActivity(intent)
        })

    }
    fun getRetroReady() {
        retrofie = Retrofit.Builder().baseUrl("https://route-ecommerce.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        apiIm = retrofie.create(ApiInterface::class.java)
        retroieCall = apiIm.getAllProducts()
        retroieCall.enqueue(object : Callback<AllProductsResponse> {
            override fun onResponse(
                call: Call<AllProductsResponse>,
                response: Response<AllProductsResponse>
            ) {
                ItemsAdapter.updateData(response.body()?.data!!) //, SubCategoryID!!
                Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
                ItemsAdapter.onItemClickListener=object : chosenCategAdapter.OnItemClickListener{
                    override fun onItemClicked(position: Int, item: DataItem2?) {
                        val Intent=Intent(this@ChosenCategItemsActivity,ProductDetailsActivity.createInstance(item!!)::class.java)
                        startActivity(Intent)
                    }

                }

            }

            override fun onFailure(call: Call<AllProductsResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
            }

        })
    }

        /*
        ItemsAdapter.onItemClickListener=object :chosenCategAdapter.OnItemClickListener{
            override fun onItemClicked(position: Int, item: choosen_categItem_data) {
                val intent=Intent(this@ChosenCategItemsActivity,ProductDetailsActivity::class.java)
                intent.putExtra(Constants.itemImgKey,ItemsData.get(position).chosenimg_dc)
                intent.putExtra(Constants.itemNameKey,ItemsData.get(position).chosenname_dc)
                intent.putExtra(Constants.itemPriceKey,ItemsData.get(position).chosenprice_dc)
                intent.putExtra(Constants.itemRatwKey,ItemsData.get(position).chosenrate_dc)
                intent.putExtra(Constants.itemDescripKey,ItemsData.get(position).chosendescrip_dc)
                startActivity(intent)
            }
        }
    }

         */


    fun InitItemData(){
        ItemsData= ArrayList()
        ItemsData.add(choosen_categItem_data(R.drawable.puma1,"Puma Sneakers","Puma Sneaker 2.0 Youth","2,0195.00","Review (4.6)"))
        ItemsData.add(choosen_categItem_data(R.drawable.puma2,"Puma Sneakers","Puma Sneaker 2.0 Youth","2,0195.00","Review (4.6)"))
        ItemsData.add(choosen_categItem_data(R.drawable.puma3,"Puma Sneakers","Puma Sneaker 2.0 Youth","2,0195.00","Review (4.6)"))
        ItemsData.add(choosen_categItem_data(R.drawable.puma4,"Puma Sneakers","Puma Sneaker 2.0 Youth","2,0195.00","Review (4.6)"))
        ItemsData.add(choosen_categItem_data(R.drawable.puma5,"Puma Sneakers","Puma Sneaker 2.0 Youth","2,0195.00","Review (4.6)"))
        ItemsData.add(choosen_categItem_data(R.drawable.puma6,"Puma Sneakers","Puma Sneaker 2.0 Youth","2,0195.00","Review (4.6)"))
    }


}