package io.abhishukla21.imgur.data.model.gallery.entity

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String,

    @SerializedName("datetime")
    val datetime: Long,

    @SerializedName("cover")
    val cover: String,

    @SerializedName("cover_width")
    val coverWidth: Int,

    @SerializedName("cover_height")
    val coverHeight: Int,

    @SerializedName("account_url")
    val accountUrl: String?,

    @SerializedName("account_id")
    val accountId: Long?,

    @SerializedName("privacy")
    val privacy: String,

    @SerializedName("layout")
    val layout: String,

    @SerializedName("views")
    val views: Long,

    @SerializedName("link")
    val link: String,

    @SerializedName("ups")
    val ups: Int?,

    @SerializedName("downs")
    val downs: Int?,

    @SerializedName("points")
    val points: Int?,

    @SerializedName("score")
    val score: Int?,

    @SerializedName("is_album")
    val isAlbum: Boolean,

    @SerializedName("vote")
    val vote: String?,

    @SerializedName("nsfw")
    val nsfw: Boolean?,

    @SerializedName("section")
    val section: String?,

    @SerializedName("favorite")
    val favorite: Boolean,

    @SerializedName("comment_count")
    val commentCount: Int?,

    @SerializedName("favorite_count")
    val favoriteCount: Int?,

    @SerializedName("images_count")
    val imagesCount: Int,

    @SerializedName("is_ad")
    val isAd: Boolean,

    @SerializedName("in_most_viral")
    val inMostViral: Boolean,

    @SerializedName("in_gallery")
    val inGallery: Boolean,

    @SerializedName("tags")
    val tags: List<Tag>?,

    @SerializedName("images")
    val images: List<Image>


)
