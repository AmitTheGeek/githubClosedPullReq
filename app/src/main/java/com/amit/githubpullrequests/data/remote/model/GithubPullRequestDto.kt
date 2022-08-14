package com.amit.githubpullrequests.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class GithubPullRequestDto(
    @SerializedName("number")
    val prNumber: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val description: String?,
    @SerializedName("created_at")
    val createdDate: Date,
    @SerializedName("closed_at")
    val closedDate: Date,
    @SerializedName("user")
    val userInfo: UserInfo
)

data class UserInfo(
    @SerializedName("login")
    val userName: String,
    @SerializedName("avatar_url")
    val userImageUrl: String
)
