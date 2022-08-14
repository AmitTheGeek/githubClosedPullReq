package com.amit.githubpullrequests.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amit.githubpullrequests.R
import com.amit.githubpullrequests.domain.githubPullRequests.GithubPullRequest
import com.amit.githubpullrequests.presentation.util.DateUtil
import com.bumptech.glide.RequestManager

class PullRequestAdapter(
    private val listClosedPullRequest: List<GithubPullRequest>,
    private val requestManager: RequestManager
) : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_pull_req, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listClosedPullRequest[position].apply {
            requestManager
                .asBitmap()
                .load(userImage)
                .into(holder.userAvatar)

            holder.userName.text = userName
            holder.title.text = title

            holder.createdAt.text = String.format("Created At: %s", DateUtil.getFormattedDate(createdDate))
            holder.closedAt.text = String.format("Closed At: %s", DateUtil.getFormattedDate(closedDated))
        }
    }

    override fun getItemCount(): Int {
        return listClosedPullRequest.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userAvatar: ImageView = itemView.findViewById(R.id.iv_userAvatar)
        val userName: TextView = itemView.findViewById(R.id.tv_username)
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val createdAt: TextView = itemView.findViewById(R.id.tv_createdAt)
        val closedAt: TextView = itemView.findViewById(R.id.tv_closedAt)
    }
}