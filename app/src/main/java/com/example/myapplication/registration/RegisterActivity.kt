package com.example.myapplication.registration

import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainActivity
//import androidx.lifecycle.ViewModelProvider
//import androidx.databinding.DataBindingUtil
//import androidx.lifecycle.ViewModelProvider
//import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.ActivityRegisterBinding
//import com.example.myapplication.databinding.ActivitySignInBinding

class RegisterActivity : AppCompatActivity(),RegisterNavigator {
    lateinit var viewBinding_Register:ActivityRegisterBinding
    var viewModelRegister:RegisterViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding_Register=ActivityRegisterBinding.inflate(layoutInflater)
        //viewBinding_Register=DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.activity_register,parent,false)
        setContentView(viewBinding_Register.root)


       /* viewBinding_Register.Login.setOnClickListener({
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        })

        */
        viewModelRegister= ViewModelProvider(this).get(RegisterViewModel::class.java)
        viewBinding_Register.vm=viewModelRegister
        viewModelRegister!!.navigator=this




    }

    override fun LoginToHomeActivity() {
        val intent=Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}