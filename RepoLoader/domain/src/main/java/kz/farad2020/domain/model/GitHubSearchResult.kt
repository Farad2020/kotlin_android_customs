package kz.farad2020.domain.model

import com.google.gson.annotations.SerializedName

data class GitHubSearchResult(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val repositories: List<GitHubRepository>
)

data class GitHubRepository(
    val name: String,
    @SerializedName("full_name") val fullName: String,
    val description: String?,
    @SerializedName("html_url") val htmlUrl: String
)
