package com.amit.githubpullrequests.data.remote

import com.amit.githubpullrequests.data.remote.model.GithubPullRequestDto
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("repos/harshita214/Chrome-Extension/pulls")
    suspend fun getGitHubPullRequests(@Query("state") state : String): List<GithubPullRequestDto>
}