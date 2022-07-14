package com.example.example

import com.google.gson.annotations.SerializedName


data class Search (

  @SerializedName("mid"      ) var mid      : Int?    = null,
  @SerializedName("code"     ) var code     : Int?    = null,
  @SerializedName("name"     ) var name     : String? = null,
  @SerializedName("barcodes" ) var barcodes : String? = null,
  @SerializedName("info"     ) var info     : String? = null

)