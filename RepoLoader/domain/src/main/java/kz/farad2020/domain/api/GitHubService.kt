package kz.farad2020.domain.api

import kz.farad2020.domain.model.GitHubSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
//    TODO -> https://docs.github.com/en/rest?apiVersion=2022-11-28
//      use https://api.github.com/search/repositories?q=your_search_keyword to get data on repos


    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): GitHubSearchResult
}