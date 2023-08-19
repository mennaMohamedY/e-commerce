package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.myapplication.adapters.colorsAdapter
import com.example.myapplication.adapters.colorsDataClass
import com.example.myapplication.cart.CartActivity
import com.example.myapplication.room.FavouriteItems
import com.example.myapplication.room.RoomClass
import com.example.myapplication.databinding.ActivityProductDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductDetailsActivity : AppCompatActivity() {
    //binding
    lateinit var bindgdPD:ActivityProductDetailsBinding
    lateinit var Room_Database:RoomClass


    companion  object{
        var data_Item:DataItem2?=null
        fun createInstance(Data_Item:DataItem2):ProductDetailsActivity{
            data_Item=Data_Item
            return ProductDetailsActivity()
        }
    }

    //lateinit var productImg:ShapeableImageView
    lateinit var productName:TextView
    lateinit var productPrice:TextView
    lateinit var howManySolds:TextView
    lateinit var productDescrip:TextView
    lateinit var productQuantity:TextView
    lateinit var baack:ImageView
    lateinit var rating:TextView
    lateinit var colorsImgsArray:ArrayList<Int>

    //slider
    lateinit var imageSlider: ImageSlider
    lateinit var images:ArrayList<SlideModel>

    lateinit var colors_recyclerView:RecyclerView
    lateinit var colors_adapter:colorsAdapter
    lateinit var colorsList:ArrayList<colorsDataClass>
    lateinit var totalPrice:TextView
    lateinit var txtView:TextView
    lateinit var addToCart:Button
    lateinit var addToCart_img:ImageView
    var co:Int?=null
    lateinit var colors_name:ArrayList<String>
    var nam:String?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        bindgdPD= ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(bindgdPD.root)

        //setContentView(R.layout.activity_product_details)
        /*
        val intent=Intent()
        val img=intent.getIntExtra(Constants.itemImgKey,R.drawable.puma1)
        val namee=intent.getStringExtra(Constants.itemNameKey)
        val price=intent.getStringExtra(Constants.itemPriceKey)
        val rate=intent.getStringExtra(Constants.itemRatwKey)
        val description=intent.getStringExtra(Constants.itemDescripKey)
        imageSlider=findViewById(R.id.image_slider)
         */
        initSliderDAta()
        bindgdPD.imageSlider.setImageList(images)
        //imageSlider.setImageList(images)
        //colors_recyclerView=findViewById(R.id.colors_recyclerView)

        initColors()
        colors_adapter= colorsAdapter(colorsList)
        colors_adapter.UpdateData(colorsList)
        bindgdPD.colorsRecyclerView.adapter=colors_adapter
        //colors_recyclerView.adapter=colors_adapter
        colors_adapter.setCheckOnSelectedInterface=object :colorsAdapter.setCheckOnSelected{

            override fun setCheck(position: Int) {
                colors_adapter.UpdateData(colorsList)
            }
        }
        /*
        addToCart_img=findViewById(R.id.addTocourt_Img)
        addToCart=findViewById(R.id.addTocart)
         */
        /*
        bindgdPD.addTocart.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                val intent=Intent(this@ProductDetailsActivity,CartActivity.createInstance(data_Item!!)::class.java)
                startActivity(intent)
            }
        })

         */
        /*
        addToCart.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                val intent=Intent(this@ProductDetailsActivity,CartActivity.createInstance(data_Item!!)::class.java)
                startActivity(intent)
            }
        })
         */
        bindgdPD.addTocourtImg.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                val intent=Intent(this@ProductDetailsActivity,CartActivity::class.java)
                startActivity(intent)
            }
        })

        colors_adapter.send_Position=object :colorsAdapter.sendPosition{
            override fun getPosition(position: Int):Int {
                Toast.makeText(this@ProductDetailsActivity,"position selected is ${position}",Toast.LENGTH_LONG).show()
                initColorImags()
                co=colorsImgsArray.get(position)
                initColorsName()
                nam=colors_name.get(position)
                return co!!
            }
        }
        /*
        addToCart_img.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                val intent=Intent(this@ProductDetailsActivity,CartActivity.createInstance(data_Item!!)::class.java)
                startActivity(intent)
            }
        })
         */


        //productImg=findViewById(R.id.product_img)
        /*
        productName=findViewById(R.id.product_name)
        productPrice=findViewById(R.id.product_price)
        howManySolds=findViewById(R.id.howManySolds)
        productDescrip=findViewById(R.id.product_description)
        productQuantity=findViewById(R.id.detailed_quantity)
        rating=findViewById(R.id.rating_average)
        baack=findViewById(R.id.back)
        totalPrice=findViewById(R.id.totalPrice)
         */


        //Glide.with(this).load(data_Item?.images?.get(0)).into(productImg)
        //productName.setText(data_Item?.subcategory?.get(0)?.name)
        bindgdPD.productName.setText(data_Item?.title)
        //productName.setText(data_Item?.title)
        bindgdPD.productPrice.setText(data_Item?.price.toString())
        bindgdPD.howManySolds.setText(data_Item?.sold.toString() + " sold")
        bindgdPD.productDescription.setText(data_Item?.slug + "\n" + data_Item?.description )
        //productDescrip.setText(data_Item?.slug + "\n" + data_Item?.description )
        bindgdPD.ratingAverage.setText(data_Item?.ratingsAverage.toString() + "("+ data_Item?.ratingsQuantity.toString()+")")
        //rating.setText(data_Item?.ratingsAverage.toString() + "("+ data_Item?.ratingsQuantity.toString()+")")
        bindgdPD.totalPrice.setText(data_Item?.price.toString())







        //productImg.setImageResource(img)
        bindgdPD.back.setOnClickListener({
            finish()
        })
        /*
        baack.setOnClickListener({
            finish()
        })
         */

        bindgdPD.addTocart.setOnClickListener{
            //Toast.makeText(this,"Item is added to Basket successfully",Toast.LENGTH_LONG).show()
            Log.e("onaddClickListener","Item is added to Basket successfully")
            addToDataBase()
            //Toast.makeText(this,"Item is added to Basket successfully",Toast.LENGTH_LONG)
        }
         /*
        bindgdPD.addTocart.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this,"Item is added to Basket successfully",Toast.LENGTH_LONG)
                addToDataBase()
            }
        })

         */
    }

    fun addToDataBase(){

        val name=bindgdPD.productName.text.toString()
        val price= bindgdPD.productPrice.text.toString().toInt()
        val size=bindgdPD.sizeTxt.text.toString()
        val Product_Img= data_Item?.images?.get(0)
        val ii="https://res.cloudinary.com/dwp0imlbj/image/upload/v1680747343/Route-Academy-categories/1681511179514.png"
        val favouriteItems=FavouriteItems(null,Product_Img,name,co,nam,size,price)
       // RoomClass.getInstance(this).FavouritsDao().addToFavourits(favouriteItems)


        GlobalScope.launch(Dispatchers.IO) {
            RoomClass.getInstance(this@ProductDetailsActivity).FavouritsDao().addToFavourits(favouriteItems)
        }
        Toast.makeText(this,"Item is added to Basket successfully",Toast.LENGTH_LONG).show()
    }

    fun initColors(){
        colorsList=ArrayList()
        colorsList.add(colorsDataClass(R.color.black))
        colorsList.add(colorsDataClass(R.color.burntRed))
        colorsList.add(colorsDataClass(R.color.bluelight))
        colorsList.add(colorsDataClass(R.color.greent))
        colorsList.add(colorsDataClass(R.color.pink))
    }
    fun initColorImags(){
        colorsImgsArray=ArrayList()
        colorsImgsArray.add(R.drawable.a_black_image)
        colorsImgsArray.add(R.drawable.burnt_umber_color)
        colorsImgsArray.add(R.drawable.canvas_true_blue)
        colorsImgsArray.add(R.drawable.forest_green_color)
        colorsImgsArray.add(R.drawable.rusty_red)
    }
    fun initColorsName(){
        colors_name= ArrayList()
        colors_name.add("Black")
        colors_name.add("Burnt-Umber Brown")
        colors_name.add("Canvas Blue")
        colors_name.add("Forest Green")
        colors_name.add("Rusty Red")
    }


    fun initSliderDAta() {
        images = ArrayList()
        var ImgSize= data_Item?.images?.size
        for (i in 0..ImgSize!!-1){
            images.add(SlideModel(data_Item?.images?.get(i)))
        }
    }
}