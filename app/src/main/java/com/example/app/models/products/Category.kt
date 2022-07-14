package com.example.app.models.products

import com.google.gson.annotations.SerializedName


data class Category (

  @SerializedName("mid"  ) var mid  : Int?    = null,
  @SerializedName("name" ) var name : String? = null

)