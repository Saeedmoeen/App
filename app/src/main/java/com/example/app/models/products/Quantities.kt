package com.example.app.models.products

import com.google.gson.annotations.SerializedName


data class Quantities (

  @SerializedName("repository_mid"  ) var repositoryMid  : Int?    = null,
  @SerializedName("repository_name" ) var repositoryName : String? = null,
  @SerializedName("quantity"        ) var quantity       : Int?    = null

)