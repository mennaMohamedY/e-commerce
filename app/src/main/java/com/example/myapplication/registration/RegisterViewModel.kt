package com.example.myapplication.registration

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.CheckFullName
import com.example.myapplication.models.CheckPhoneNum
import com.example.myapplication.models.checkEmail
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel:ViewModel() {

    var Password=ObservableField<String>()
    var passwordError=ObservableField<String>()

    var Fullname=ObservableField<String>()
    var fullNamedError=ObservableField<String>()


    var email=ObservableField<String>()
    var emailError=ObservableField<String>()


    var phoneNumber=ObservableField<String>()
    var phoneNumberError=ObservableField<String>()
    val auth=FirebaseAuth.getInstance()
    var navigator:RegisterNavigator?=null

    fun SignIn(){
        if (validation()){
            auth.createUserWithEmailAndPassword(email.get()!!,Password.get()!!).addOnCompleteListener {
                if(it.isSuccessful){
                    navigator?.LoginToHomeActivity()
                }
            }
        }
    }


    fun validation():Boolean{
        var valid=true
        var valid1=true
        var valid2=true
        var valid3=true
        var valid4=true

        if(email.get().isNullOrEmpty()){
            valid1=false
            emailError.set("Please Enter password")
            //show Error msg
        }else if(email!=null){
            if((checkEmail(email.get().toString()))){
                valid1= true
                emailError.set(null)
            }else{
                valid1=false
                emailError.set("Enter a valid Email ex menna.yousef@yahoo.com")
            }
        }

        if(phoneNumber.get().isNullOrEmpty()){
            valid2=false
            phoneNumberError.set("Please Enter Your PhoneNumber")
        }else if(phoneNumber!=null){
            if(CheckPhoneNum(phoneNumber.get()!!)){
                valid2= true
                phoneNumberError.set(null)
            }else{
                valid2=false
                phoneNumberError.set("Enter a valid phoneNum!")
            }
        }
        if(Fullname.get().isNullOrEmpty()){
            valid3=false
            fullNamedError.set("Please Enter FullName")
        }else if(CheckFullName(Fullname.get().toString())){
            if(CheckFullName(Fullname.get()!!)){
                valid3= true
                fullNamedError.set(null)
            }else{
                valid3=false
                fullNamedError.set("Enter your full-name!")
            }
        }

        if(Password.get().isNullOrEmpty()){
            valid4=false
            passwordError.set("Please Enter Your PhoneNumber")
        }else if(Password!=null){
            if(Password.get()!!.length<6){
                valid4=false
                passwordError.set("Password must be more than 6 characters")
            }else{
                passwordError.set(null)
            }
        }

        if (valid1 && valid2 && valid3 && valid4){
            valid=true
        }else {valid=false}
        return valid
    }


}