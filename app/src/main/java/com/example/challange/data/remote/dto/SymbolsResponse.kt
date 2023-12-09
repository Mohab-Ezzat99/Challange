package com.example.challange.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SymbolsResponse (

    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("symbols" ) var symbols : Symbols? = Symbols()

)

data class Symbols (

    @SerializedName("AED" ) var AED : String? = null,
    @SerializedName("AUD" ) var AUD : String? = null,
    @SerializedName("EGP" ) var EGP : String? = null,
    @SerializedName("EUR" ) var EUR : String? = null,
    @SerializedName("USD" ) var USD : String? = null

)