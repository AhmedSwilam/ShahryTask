package com.shahry.com.eg.model

import com.google.gson.annotations.SerializedName


data class Address(

    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("longitude") var longitude: String? = null

)