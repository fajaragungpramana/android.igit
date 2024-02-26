package com.github.fajaragungpramana.igit.core.domain.user.model

import androidx.recyclerview.widget.DiffUtil
import com.github.fajaragungpramana.igit.core.data.remote.user.response.RepoResponse

data class Repo(
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var starCount: Int? = null
) {

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Repo>() {

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.id == newItem.id

            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem == newItem

        }

        fun mapToObject(repo: RepoResponse) = Repo(
            name = repo.name,
            description = repo.description,
            starCount = repo.stargazersCount
        )

    }

}