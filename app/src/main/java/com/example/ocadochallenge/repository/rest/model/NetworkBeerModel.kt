package com.example.brewdogbeers.repository.rest.model

import com.google.gson.annotations.SerializedName

data class NetworkBeerModel(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("first_brewed")
    val first_brewed: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("image_url")
    val image_url: String? = null,
    @SerializedName("abv")
    val abv: Float? = null,
    @SerializedName("ibu")
    val ibu: Float? = null,
    @SerializedName("target_fg")
    val target_fg: Float? = null,
    @SerializedName("target_og")
    val target_og: Float? = null,
    @SerializedName("ebc")
    val ebc: Float? = null,
    @SerializedName("srm")
    val srm: Float? = null,
    @SerializedName("ph")
    val ph: Float? = null,
    @SerializedName("attenuation_level")
    val attenuation_level: Float? = null,
    @SerializedName("volume")
    val volume: QuantityValue? = null,
    @SerializedName("boil_volume")
    val boil_volume: QuantityValue? = null,
    @SerializedName("method")
    val method: BeerMethod? = null,
    @SerializedName("ingredients")
    val ingredients: BeerIngredients? = null,
    @SerializedName("food_pairing")
    val food_pairing: List<String>? = null,
    @SerializedName("brewers_tips")
    val brewers_tips: String? = null,
    @SerializedName("contributed_by")
    val contributed_by: String? = null
)
