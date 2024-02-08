package com.example.milladmin.Database

data class UsersDataModel(
    val userName: String,
    val phoneNum : Int,
)

data class MillData(
    val PiID:String,
    val name:String,
    val status:String,
    val steamPress:String,
    val SteamFlow:String,
    val WtrLevel:String,
    val PwrFrq:String,
    val mode:String,
    val proxyport:String,
    val tunnelport:String,
)

data class UserData(
    val name:String,
    val phoneNum: String,
)

data class newUserData(
    val name:String,
    val phoneNum:String,
    val status:String,
    val role:String,
    val state:String,
)

data class newMillData(
    val PiID:String,
    val name:String,
    val status:String,
    val steamPress:String,
    val SteamFlow:String,
    val WtrLevel:String,
    val PwrFrq:String,
    val mode:String,
    val proxyport:String,
    val tunnelport:String,
)

data class fetchedMillData(
    val PiID:String,
    val name:String,
    val status:String,
    val steamPress:String,
    val SteamFlow:String,
    val WtrLevel:String,
    val PwrFrq:String,
    val mode:String,
    val proxyport:String,
    val tunnelport:String,
)
