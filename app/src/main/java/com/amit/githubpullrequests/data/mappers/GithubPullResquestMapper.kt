package com.amit.githubpullrequests.data.mappers

import com.amit.githubpullrequests.data.remote.model.GithubPullRequestDto
import com.amit.githubpullrequests.domain.githubPullRequests.GithubPullRequest


fun GithubPullRequestDto.toGithubPullRequest(): GithubPullRequest {
    val title = this.title
    val createdDate = this.createdDate
    val closedDated = this.closedDate
    val userName = this.userInfo.userName
    val userImage = this.userInfo.userImageUrl

    return GithubPullRequest(
        title = title,
        createdDate = createdDate,
        closedDated = closedDated,
        userName = userName,
        userImage = userImage
    )
}