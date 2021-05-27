package com.vmurillot.limeade.model

data class UserData(
    var firstName:String = "",
    var lasttName:String  = "",
    var Phone:String = "",
    var userName:String,
    var password:String,
    var isEnable:Boolean
) {
}