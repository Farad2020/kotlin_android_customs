package kz.farad2020.domain.api

import kz.farad2020.domain.model.GitHubSearchResult
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GitHubApiService {
//    TODO -> https://docs.github.com/en/rest?apiVersion=2022-11-28
//      use https://api.github.com/search/repositories?q=real to get data on repos


    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): Response<GitHubSearchResult>


    @GET
    suspend fun downloadRepository(@Url fullUrl: String): ResponseBody
}