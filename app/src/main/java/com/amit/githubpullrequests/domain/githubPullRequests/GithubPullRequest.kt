package com.amit.githubpullrequests.domain.githubPullRequests

import java.util.*

// class for representing required data to UI
data class GithubPullRequest(
    val title: String,
    val createdDate: Date,
    val closedDated: Date,
    val userName: String,
    val userImage: String
)
