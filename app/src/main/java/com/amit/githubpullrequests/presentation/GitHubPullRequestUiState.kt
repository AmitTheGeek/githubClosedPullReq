package com.amit.githubpullrequests.presentation

import com.amit.githubpullrequests.domain.githubPullRequests.GithubPullRequest

sealed class GitHubPullRequestUiState {
    class Loading(val isLoading: Boolean) : GitHubPullRequestUiState()
    class PayLoad(val assetList: List<GithubPullRequest>) : GitHubPullRequestUiState()
    class ErrorState(val error: String) : GitHubPullRequestUiState()
}