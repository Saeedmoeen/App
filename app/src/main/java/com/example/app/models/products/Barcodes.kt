package com.example.app.models.products

import com.google.gson.annotations.SerializedName


data class Barcodes (

  @SerializedName("mid"  ) var mid  : Int?    = null,
  @SerializedName("text" ) var text : String? = null

)