package com.open.warehouseandinventory.model

import com.google.gson.annotations.SerializedName


data class BarcodeLookupResponse (

  @SerializedName("barcode_number"          ) var barcodeNumber         : String?           = null,
  @SerializedName("barcode_formats"         ) var barcodeFormats        : String?           = null,
  @SerializedName("mpn"                     ) var mpn                   : String?           = null,
  @SerializedName("model"                   ) var model                 : String?           = null,
  @SerializedName("asin"                    ) var asin                  : String?           = null,
  @SerializedName("title"                   ) var title                 : String?           = null,
  @SerializedName("category"                ) var category              : String?           = null,
  @SerializedName("manufacturer"            ) var manufacturer          : String?           = null,
  @SerializedName("brand"                   ) var brand                 : String?           = null,
  @SerializedName("contributors"            ) var contributors          : ArrayList<String> = arrayListOf(),
  @SerializedName("age_group"               ) var ageGroup              : String?           = null,
  @SerializedName("ingredients"             ) var ingredients           : String?           = null,
  @SerializedName("nutrition_facts"         ) var nutritionFacts        : String?           = null,
  @SerializedName("energy_efficiency_class" ) var energyEfficiencyClass : String?           = null,
  @SerializedName("color"                   ) var color                 : String?           = null,
  @SerializedName("gender"                  ) var gender                : String?           = null,
  @SerializedName("material"                ) var material              : String?           = null,
  @SerializedName("pattern"                 ) var pattern               : String?           = null,
  @SerializedName("format"                  ) var format                : String?           = null,
  @SerializedName("multipack"               ) var multipack             : String?           = null,
  @SerializedName("size"                    ) var size                  : String?           = null,
  @SerializedName("length"                  ) var length                : String?           = null,
  @SerializedName("width"                   ) var width                 : String?           = null,
  @SerializedName("height"                  ) var height                : String?           = null,
  @SerializedName("weight"                  ) var weight                : String?           = null,
  @SerializedName("release_date"            ) var releaseDate           : String?           = null,
  @SerializedName("description"             ) var description           : String?           = null,
  @SerializedName("features"                ) var features              : ArrayList<String> = arrayListOf(),
  @SerializedName("images"                  ) var images                : ArrayList<String> = arrayListOf(),
  @SerializedName("last_update"             ) var lastUpdate            : String?           = null,
  @SerializedName("stores"                  ) var stores                : ArrayList<Stores> = arrayListOf(),
  @SerializedName("reviews"                 ) var reviews               : ArrayList<String> = arrayListOf()

)