package com.example.app.models.products

import com.google.gson.annotations.SerializedName


data class Results (

    @SerializedName("mid"             ) var mid            : Int?                  = null,
    @SerializedName("code"            ) var code           : Int?                  = null,
    @SerializedName("name"            ) var name           : String?               = null,
    @SerializedName("active"          ) var active         : Boolean?              = null,
    @SerializedName("info"            ) var info           : String?               = null,
    @SerializedName("unit_type"       ) var unitType       : Int?                  = null,
    @SerializedName("unit1"           ) var unit1          : String?               = null,
    @SerializedName("unit2"           ) var unit2          : String?               = null,
    @SerializedName("unit2_in_unit1"  ) var unit2InUnit1   : String?               = null,
    @SerializedName("taxable"         ) var taxable        : Boolean?              = null,
    @SerializedName("visitor_percent" ) var visitorPercent : String?               = null,
    @SerializedName("category"        ) var category       : Category?             = Category(),
    @SerializedName("barcodes"        ) var barcodes       : ArrayList<Barcodes>   = arrayListOf(),
    @SerializedName("prices"          ) var prices         : ArrayList<Prices>     = arrayListOf(),
    @SerializedName("quantities"      ) var quantities     : ArrayList<Quantities> = arrayListOf()

)