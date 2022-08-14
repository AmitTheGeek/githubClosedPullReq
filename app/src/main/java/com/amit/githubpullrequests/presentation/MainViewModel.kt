package com.amit.githubpullrequests.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amit.githubpullrequests.domain.repository.GithubPrRepository
import com.amit.githubpullrequests.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubPrRepository
) : ViewModel() {

    private val _event = Channel<GitHubPullRequestUiState>()
    val event = _event.receiveAsFlow()


    fun getAllPullRequests() {
        viewModelScope.launch(Dispatchers.IO) {
            _event.send(GitHubPullRequestUiState.Loading(true))

            when (val response = repository.getGithubPullRequests()) {
                is Resource.Error -> {
                    _event.send(
                        GitHubPullRequestUiState.ErrorState(
                            response.message ?: "An unknown error occurred."
                        )
                    )
                }

                is Resource.Success -> {
                    response.data?.let { _event.send(GitHubPullRequestUiState.PayLoad(it)) }
                }
            }
            _event.send(GitHubPullRequestUiState.Loading(false))
        }
    }
}