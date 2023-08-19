package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.fragment.app.Fragment
import com.example.myapplication.cart.CartActivity
import com.example.myapplication.fragments.HomeScreenFragment
import com.example.myapplication.profileinfo.PersonaLInfoActivity
import com.example.myapplication.signin.signInActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        pushFragment(HomeScreenFragment())
        onItemClicked()
        binding.courtImg.setOnClickListener(object :OnClickListener{
            override fun onClick(v: View?) {
                val intent=Intent(this@MainActivity, CartActivity::class.java)
                startActivity(intent)
            }
        })

        binding.courtImg.setOnClickListener({
            val intent=Intent(this,CartActivity::class.java)
            startActivity(intent)
        })
        binding.routeSignature.setOnClickListener({
            val inten=Intent(this,signInActivity::class.java)
            startActivity(inten)
        })
    }


    fun onItemClicked(){

        binding.bottomNavigationView.setOnItemSelectedListener{
            if (it.itemId==R.id.home){
                pushFragment(HomeScreenFragment())
            }else if(it.itemId==R.id.categories){
                val intent =Intent(this,SideDrawerActivity::class.java)
                startActivity(intent)
               // pushFragment(CategoriesFragment())
            }else if(it.itemId==R.id.profile){
                val intent_p=Intent(this,PersonaLInfoActivity::class.java)
                startActivity(intent_p)
            }
            return@setOnItemSelectedListener true
        }


    }

    fun pushFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.framLayout,fragment).commit()
    }


}