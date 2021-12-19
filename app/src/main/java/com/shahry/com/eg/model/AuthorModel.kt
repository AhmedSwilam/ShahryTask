package com.shahry.com.eg.model

import com.google.gson.annotations.SerializedName


data class AuthorModel(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("userName") var userName: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("avatarUrl") var avatarUrl: String? = null,
    @SerializedName("address") var address: Address? = Address()

)