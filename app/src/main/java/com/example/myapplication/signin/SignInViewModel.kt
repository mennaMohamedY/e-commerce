package com.example.myapplication.signin

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class SignInViewModel:ViewModel() {
     val password=ObservableField<String>()
    val userName= ObservableField<String>()
    val errorMsg=ObservableField<String>()
    val usernameError=ObservableField<String>()
    val passwordError=ObservableField<String>()




}