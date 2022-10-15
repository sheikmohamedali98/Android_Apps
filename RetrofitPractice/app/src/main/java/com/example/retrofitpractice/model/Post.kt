package com.example.retrofitpractice.model


data class Post(

    val userId:Int,
    val id:Int,
    val title:String,
    val body:String
//    val number:Int,
//    val sect_tam:String,
//    val chapgrp_tam: String?,
//    val chap_tam: String?,
//    val line1:String,
//    val line2:String,
//    val tam_exp:String,
//    @Transient val sect_eng: String? = null,
//    @Transient val chapgrp_eng: String? = null,
//    @Transient val chap_eng: String? = null,
//    @Transient val eng: String? = null,
//    @Transient val eng_exp: String? = null
)