package com.amit.githubpullrequests.domain.repository

import com.amit.githubpullrequests.domain.githubPullRequests.GithubPullRequest
import com.amit.githubpullrequests.domain.util.Resource

interface GithubPrRepository {

    suspend fun getGithubPullRequests(): Resource<List<GithubPullRequest>>

}