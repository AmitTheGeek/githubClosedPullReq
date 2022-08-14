package com.amit.githubpullrequests.data.repository

import com.amit.githubpullrequests.data.mappers.toGithubPullRequest
import com.amit.githubpullrequests.data.remote.ApiService
import com.amit.githubpullrequests.domain.githubPullRequests.GithubPullRequest
import com.amit.githubpullrequests.domain.repository.GithubPrRepository
import com.amit.githubpullrequests.domain.util.Resource
import javax.inject.Inject

private const val TAG = "GithubPrRepositoryImpl"
private const val STATE_CLOSED = "closed"

class GithubPrRepositoryImpl @Inject constructor(
    private val api: ApiService
) : GithubPrRepository {

    override suspend fun getGithubPullRequests(): Resource<List<GithubPullRequest>> {
        return try {
            val response = api.getGitHubPullRequests(STATE_CLOSED)
            Resource.Success(response.map { it.toGithubPullRequest() })
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}