package com.example.myapplication.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.MainActivity
import com.example.myapplication.registration.RegisterActivity
import com.example.myapplication.databinding.ActivitySignInBinding

class signInActivity : AppCompatActivity() {
    lateinit var viewBinding_SignIn:ActivitySignInBinding
    lateinit var viewModel:SignInViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding_SignIn=ActivitySignInBinding.inflate(layoutInflater)
        setContentView(viewBinding_SignIn.root)
        //viewBinding= DataBindingUtil.setContentView(this,R.layout.activity_sign_in)
        //viewModel=ViewModelProvider(this).get(SignInViewModel::class.java)

        //viewBinding.vm=viewModel

        viewBinding_SignIn.Login.setOnClickListener({
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        })

        viewBinding_SignIn.donthaveAcc.setOnClickListener({
            val inten=Intent(this,RegisterActivity::class.java)
            startActivity(inten)
        })
    }


}