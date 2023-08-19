package com.example.myapplication.profileinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityPersonaLinfoBinding

class PersonaLInfoActivity : AppCompatActivity() {
    lateinit var viewBindin_PersonalInfo:ActivityPersonaLinfoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBindin_PersonalInfo=ActivityPersonaLinfoBinding.inflate(layoutInflater)
        setContentView(viewBindin_PersonalInfo.root)

        viewBindin_PersonalInfo.backk.setOnClickListener({
            finish()
        })
    }
}