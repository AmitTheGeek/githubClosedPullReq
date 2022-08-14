package com.amit.githubpullrequests.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amit.githubpullrequests.R
import com.amit.githubpullrequests.domain.githubPullRequests.GithubPullRequest
import com.amit.githubpullrequests.presentation.adapter.PullRequestAdapter
import com.amit.githubpullrequests.presentation.util.NetworkUtil
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private var mProgressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mProgressBar = findViewById(R.id.progress_circular)
        observeState()
        loadData()
    }

    private fun loadData() {
        if (NetworkUtil.isNetworkAvailable(this)) {
            viewModel.getAllPullRequests()
        } else {
            Toast.makeText(this, "Internet Connection not available", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeState() {
        lifecycleScope.launchWhenCreated {
            viewModel.event.collect { state ->
                when (state) {
                    is GitHubPullRequestUiState.ErrorState -> {
                        Toast.makeText(this@MainActivity, state.error, Toast.LENGTH_SHORT).show()
                    }
                    is GitHubPullRequestUiState.Loading -> mProgressBar?.isVisible = state.isLoading
                    is GitHubPullRequestUiState.PayLoad -> {
                        showData(state.assetList)
                    }
                }
            }
        }
    }

    private fun showData(listClosedRequest: List<GithubPullRequest>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView?.apply {
            recyclerView.removeAllViews()
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PullRequestAdapter(listClosedRequest, Glide.with(this@MainActivity))
        }
    }
}