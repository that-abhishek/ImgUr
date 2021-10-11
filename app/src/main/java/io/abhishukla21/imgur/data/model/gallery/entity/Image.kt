package io.abhishukla21.imgur.data.model.gallery.entity

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String,

    @SerializedName("datetime")
    val datetime: Long,

    @SerializedName("type")
    val type: String,

    @SerializedName("animated")
    val animated: Boolean,

    @SerializedName("width")
    val width: Int,

    @SerializedName("height")
    val height: Int,

    @SerializedName("size")
    val size: Long,

    @SerializedName("views")
    val views: Long,

    @SerializedName("bandwidth")
    val bandwidth: Long,

    @SerializedName("vote")
    val vote: String?,

    @SerializedName("favorite")
    val favorite: Boolean,

    @SerializedName("nsfw")
    val nsfw: Boolean?,

    @SerializedName("section")
    val section: String?,

    @SerializedName("account_url")
    val accountUrl: String?,

    @SerializedName("account_id")
    val accountId: Long?,

    @SerializedName("is_ad")
    val isAd: Boolean,

    @SerializedName("in_most_viral")
    val inMostViral: Boolean,

    @SerializedName("has_sound")
    val hasSound: Boolean,

    @SerializedName("in_gallery")
    val inGallery: Boolean,

    @SerializedName("link")
    val link: String,

    @SerializedName("mp4_size")
    val mp4Size: Long?,

    @SerializedName("mp4")
    val mp4: String?,

    @SerializedName("gifv")
    val gifv: String?,

    @SerializedName("hsl")
    val hls: String?,

    @SerializedName("comment_count")
    val commentCount: Int?,

    @SerializedName("favorite_count")
    val favoriteCount: Int?,

    @SerializedName("ups")
    val ups: Int?,

    @SerializedName("downs")
    val downs: Int?,

    @SerializedName("points")
    val points: Int?,

    @SerializedName("score")
    val score: Int?


)
