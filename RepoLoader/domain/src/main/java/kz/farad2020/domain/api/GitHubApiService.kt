package kz.farad2020.domain.api

import kz.farad2020.domain.model.GitHubSearchResult
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GitHubApiService {
    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): Response<GitHubSearchResult>


    @GET
    suspend fun downloadRepository(@Url fullUrl: String): ResponseBody
}