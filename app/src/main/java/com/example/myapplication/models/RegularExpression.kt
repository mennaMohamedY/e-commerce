package com.example.myapplication.models

import java.util.regex.Pattern

val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

fun checkEmail(email: String): Boolean {
    return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
}

val fullName_PATTERN:Pattern=Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            " " +"[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    " " +
                    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
)
fun CheckFullName(userName:String):Boolean{
    return fullName_PATTERN.matcher(userName).matches()
}
val PHONENUMBER_PATTERN:Pattern=Pattern.compile(
    "[0-9]{11}"
)
fun CheckPhoneNum(PhoneNum:String):Boolean{
    return PHONENUMBER_PATTERN.matcher(PhoneNum).matches()
}