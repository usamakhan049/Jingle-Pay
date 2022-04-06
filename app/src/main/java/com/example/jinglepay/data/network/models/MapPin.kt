package com.example.jinglepay.data.network.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MapPin(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("address") var address: Address? = Address(),
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("website") var website: String? = null,
    @SerializedName("company") var company: Company? = Company()
) : Serializable


data class Geo(
    @SerializedName("lat") var lat: String? = null,
    @SerializedName("lng") var lng: String? = null
) : Serializable


data class Address(
    @SerializedName("street") var street: String? = null,
    @SerializedName("suite") var suite: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("zipcode") var zipcode: String? = null,
    @SerializedName("geo") var geo: Geo? = Geo()
) : Serializable

data class Company(
    @SerializedName("name") var name: String? = null,
    @SerializedName("catchPhrase") var catchPhrase: String? = null,
    @SerializedName("bs") var bs: String? = null
) : Serializable
