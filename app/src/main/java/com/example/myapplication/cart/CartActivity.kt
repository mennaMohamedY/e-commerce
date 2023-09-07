package com.example.myapplication.cart

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.room.FavouriteItems
import com.example.myapplication.room.RoomClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartActivity : AppCompatActivity() {


    //var ArraYHoldsData:ArrayList<DataItem2?>?=null
    lateinit var courtAdapter: CartAdapter
    lateinit var CourtrecyclerView:RecyclerView
    lateinit var CourtArrayDAta:ArrayList<CartDataClass>
    lateinit var FavouritsDAta:List<FavouriteItems>
    lateinit var deletImg:ImageView
    lateinit var TotalPrice:TextView

    var Prices:List<Int>?=null
    var count_Total:Int?=null
/*
    companion  object{
        var data_Item: DataItem2?=null
        fun createInstance(Data_Item: DataItem2?): CartActivity {
            data_Item=Data_Item
            return CartActivity()
        }
    }

 */
    /*
    fun addDataToArray(dataItem2: DataItem2?):ArrayList<DataItem2?>?{
        ArraYHoldsData= ArrayList()
        ArraYHoldsData?.add(dataItem2!!)
        return ArraYHoldsData
    }

     */

   // lateinit var binding:SingleCortItemBinding
    lateinit var backbtn:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        backbtn=findViewById(R.id.back)
        initCourtData()
        CourtrecyclerView=findViewById(R.id.court_recyclerView)
        //val CourtArrayData=addDataToArray(data_Item!!)
        deletImg=findViewById(R.id.Delet_All_products)

        courtAdapter=CartAdapter(null)
        TotalPrice=findViewById(R.id.totalPrice_court)
        //courtAdapter.UpdateData(CourtArrayData)
        //courtAdapter=CartAdapter(data_Item!!)
        //courtAdapter=CartAdapter(CourtArrayDAta)
        /*
        courtAdapter.add_TheImg=object :CartAdapter.addTheImg{
            override fun addImg(position: Int, img_view: ImageView) {
                val ig ="https://res.cloudinary.com/dwp0imlbj/image/upload/v1680747343/Route-Academy-categories/1681511179514.png"
                Glide.with(this@CartActivity).load(ig).into(img_view)
            }

        }


         */

        //var total=calAllPricess(pricesIn_court)
        //TotalPrice.text=total.toString()




        CourtrecyclerView.adapter=courtAdapter
        getFavouritData()


        getAllProcies()
        /*
        val FavouritsDAta=RoomClass.getInstance(this@CartActivity).FavouritsDao().showAllFromFavourits()
        courtAdapter.UpdateData(FavouritsDAta)


        GlobalScope.launch(Dispatchers.IO) {
            FavouritsDAta= RoomClass.getInstance(this@CartActivity).FavouritsDao().showAllFromFavourits()
        }

 */

        //courtAdapter.UpdateData(FavouritsDAta)

        backbtn.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                finish()
            }
        })
        deletImg.setOnClickListener({
            deletAllFromDataBase()
            getAllProcies()
            finish()
        })

        courtAdapter.onDeleteClickListener=object :CartAdapter.OnDeleteClickListener{
            override fun onDelete(position: Int, fav_item: FavouriteItems) {
                deletCertainFavItem(position,fav_item)
                getAllProcies()
            }

        }

       //binding=DataBindingUtil.setContentView(this,R.layout.single_cort_item)
    }



    fun deletAllFromDataBase(){
        val Favourits_Data=getFavouritData()
        GlobalScope.launch(Dispatchers.IO){
            RoomClass.getInstance(this@CartActivity).FavouritsDao().deleteAllFromDataBase()
        }
    }

    fun getFavouritData(){
        GlobalScope.launch(Dispatchers.IO) {
            FavouritsDAta= RoomClass.getInstance(this@CartActivity).FavouritsDao().showAllFromFavourits()

            courtAdapter.UpdateData(FavouritsDAta)

            /*
            runOnUiThread {
                // Stuff that updates the UI
                courtAdapter.UpdateData(FavouritsDAta)
            }

             */
        }}

    fun deletCertainFavItem(poition:Int,Fav:FavouriteItems) {
        GlobalScope.launch(Dispatchers.IO) {
            RoomClass.getInstance(this@CartActivity).FavouritsDao().deleteItemFromFav(Fav)


            runOnUiThread {
                // Stuff that updates the UI
                courtAdapter.updateDataOnRemove(poition)
                getFavouritData()
                getAllProcies()

            }


        }
    }

    fun getAllProcies(){
        GlobalScope.launch(Dispatchers.IO){
            //Prices=RoomClass.getInstance(this@CartActivity).FavouritsDao().getAllPrices()
            Prices=RoomClass.getInstance(this@CartActivity).FavouritsDao().getAllPrices()
            /*
            var fv = calAllPricess(Prices!!)
            TotalPrice.text="${fv}"

             */


            runOnUiThread {
                // Stuff that updates the UI
                var fv = calAllPricess(Prices!!)
                TotalPrice.text="${fv}"
            }


        }
    }
    fun calAllPricess(prices:List<Int>):Int{
        count_Total=0
        if(prices.size>=1){
            for (i in 0..prices.size-1){
                count_Total = count_Total?.plus(prices.get(i))
            }
        }

        return count_Total!!
    }
        //return FavouritsDAta
        //val FavouritsDAta=RoomClass.getInstance(this@CartActivity).FavouritsDao().showAllFromFavourits()
        //courtAdapter.UpdateData(FavouritsDAta)



    fun initCourtData(){
        CourtArrayDAta= ArrayList()
        CourtArrayDAta.add(CartDataClass(R.drawable.mbags,"FAshion",R.color.pink,"Orange","4200"))
        CourtArrayDAta.add(CartDataClass(R.drawable.mwatches,"FAshion",R.color.greent,"Orange","4200"))
        CourtArrayDAta.add(CartDataClass(R.drawable.mwatches,"FAshion",R.color.teal_200,"Orange","4200"))
        CourtArrayDAta.add(CartDataClass(R.drawable.mfootwear,"FAshion",R.color.black,"Orange","4200"))
    }




}