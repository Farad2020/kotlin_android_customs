package kz.farad2020.domain.model

import com.google.gson.annotations.SerializedName
import kz.farad2020.domain.model.AppBaseUrls.GITHUB_URL

data class GitHubSearchResult(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val repositories: List<GitHubRepository>
)

data class GitHubRepository(
    val name: String,
    val description: String?,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("default_branch") private val defaultBranch: String,
){
//    TODO check if works with GITHUB_API_URL
    fun getDownloadLink(): String{
        return "${GITHUB_URL}/${fullName}/archive/refs/heads/${defaultBranch}.zip"
    }

    fun getLogin(): String{
        return fullName.substringBefore("/")
    }
}
