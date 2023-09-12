package com.open.warehouseandinventory.model

import com.google.gson.annotations.SerializedName


data class Stores (

  @SerializedName("name"            ) var name           : String?           = null,
  @SerializedName("country"         ) var country        : String?           = null,
  @SerializedName("currency"        ) var currency       : String?           = null,
  @SerializedName("currency_symbol" ) var currencySymbol : String?           = null,
  @SerializedName("price"           ) var price          : String?           = null,
  @SerializedName("sale_price"      ) var salePrice      : String?           = null,
  @SerializedName("tax"             ) var tax            : ArrayList<String> = arrayListOf(),
  @SerializedName("link"            ) var link           : String?           = null,
  @SerializedName("item_group_id"   ) var itemGroupId    : String?           = null,
  @SerializedName("availability"    ) var availability   : String?           = null,
  @SerializedName("condition"       ) var condition      : String?           = null,
  @SerializedName("shipping"        ) var shipping       : ArrayList<String> = arrayListOf(),
  @SerializedName("last_update"     ) var lastUpdate     : String?           = null

)