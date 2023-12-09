package com.example.challange.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LatestResponse(

    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("timestamp") var timestamp: Int? = null,
    @SerializedName("base") var base: String? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("rates") var rates: Rates? = Rates()

)


data class Rates(
    @SerializedName("EUR") var EUR: Double? = null,
    @SerializedName("AED") var AED: Double? = null,
    @SerializedName("AUD") var AUD: Double? = null,
    @SerializedName("EGP") var EGP: Double? = null,
    @SerializedName("USD") var USD: Double? = null,
)