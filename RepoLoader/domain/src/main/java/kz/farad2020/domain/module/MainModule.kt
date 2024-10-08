package kz.farad2020.domain.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kz.farad2020.domain.api.GitHubApiService
import kz.farad2020.domain.model.AppBaseUrls
import kz.farad2020.domain.repo.MainRepoImpl
import kz.farad2020.domain.repo.MainRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


//Check if should set different lifetime, like ActivityComponent via abstract class.
// Also change usage of ApplicationContext if you do
@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LoggerInterceptorOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MainOkHttpOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MainRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class GitHubApiServiceQual



    @Singleton
    @Provides
    @LoggerInterceptorOkHttpClient
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
//            TODO add check for debug version
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
    }


    @Singleton
    @Provides
    @MainOkHttpOkHttpClient
    fun provideOkHttp(
        @LoggerInterceptorOkHttpClient interceptor: HttpLoggingInterceptor
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    @MainRetrofit
    fun provideRetrofit(
        @MainOkHttpOkHttpClient okHttpClient: OkHttpClient
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(AppBaseUrls.GITHUB_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Singleton
    @Provides
    @GitHubApiServiceQual
    fun provideGitHubApiService(
        @MainRetrofit retrofit: Retrofit
    ): GitHubApiService = retrofit.create(GitHubApiService::class.java)


    @Singleton
    @Provides
    fun provideMainRepository(
        @GitHubApiServiceQual apiService: GitHubApiService,
        @ApplicationContext context: Context
    ): MainRepository = MainRepoImpl(apiService, context)

}