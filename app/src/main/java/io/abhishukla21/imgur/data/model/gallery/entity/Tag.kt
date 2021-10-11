package io.abhishukla21.imgur.data.model.gallery.entity

import com.google.gson.annotations.SerializedName

data class Tag(

    @SerializedName("name")
    val name: String,

    @SerializedName("display_name")
    val displayName: String,

    @SerializedName("followers")
    val followers: Int,

    @SerializedName("total_items")
    val totalItems: Int,

    @SerializedName("following")
    val following: Boolean,

    @SerializedName("is_Whitelisted")
    val isWhitelisted: Boolean,

    @SerializedName("background_hash")
    val backgroundHash: String,

    @SerializedName("thumbnail_hash")
    val thumbnailHash: String,

    @SerializedName("accent")
    val accent: String,

    @SerializedName("background_is_animated")
    val backgroundIsAnimated: Boolean,

    @SerializedName("thumbnail_is_animated")
    val thumbnailIsAnimated: Boolean,


)
