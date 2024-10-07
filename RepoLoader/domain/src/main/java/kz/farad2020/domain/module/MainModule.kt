package kz.farad2020.domain.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.farad2020.domain.api.GitHubApiService
import kz.farad2020.domain.model.AppBaseUrls
import kz.farad2020.domain.repo.MainRepoImpl
import kz.farad2020.domain.repo.MainRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


//Check if should set different lifetime, like ActivityComponent via abstract class
@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Singleton
    @Provides
    @Named("LoggingInterceptor")
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
//            TODO add check for debug version
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
    }


    @Singleton
    @Provides
    @Named("MainOkHttp")
    fun provideOkHttp(
        @Named("LoggingInterceptor") interceptor: HttpLoggingInterceptor
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    @Named("MainRetrofit")
    fun provideRetrofit(
        @Named("MainOkHttp") okHttpClient: OkHttpClient
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(AppBaseUrls.GITHUB_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Singleton
    @Provides
    @Named("GitHubService")
    fun provideGitHubApiService(
        @Named("MainRetrofit") retrofit: Retrofit
    ): GitHubApiService = retrofit.create(GitHubApiService::class.java)


    @Singleton
    @Provides
    fun provideMainRepository(
        @Named("GitHubService") apiService: GitHubApiService
    ): MainRepository = MainRepoImpl(apiService)

}