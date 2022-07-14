package com.example.app.models.products

import com.google.gson.annotations.SerializedName


data class Prices (

  @SerializedName("mid"              ) var mid             : Int? = null,
  @SerializedName("price_level"      ) var priceLevel      : Int? = null,
  @SerializedName("price_type"       ) var priceType       : Int? = null,
  @SerializedName("price"            ) var price           : Int? = null,
  @SerializedName("discount_percent" ) var discountPercent : Int? = null,
  @SerializedName("discount_value"   ) var discountValue   : Int? = null,
  @SerializedName("final_price"      ) var finalPrice      : Int? = null

)