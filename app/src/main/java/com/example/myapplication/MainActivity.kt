package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.Fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView=findViewById(R.id.bottomNavigationView)
        onItemClicked()
    }


    fun onItemClicked(){
        bottomNavigationView.setOnItemSelectedListener{
            if (it.itemId==R.id.home){
                pushFragment(HomeFragment())
            }
            return@setOnItemSelectedListener true
        }


    }

    fun pushFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.framLayout,fragment).commit()
    }


}