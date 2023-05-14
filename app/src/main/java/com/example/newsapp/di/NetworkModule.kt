package com.example.newsapp.di

import com.example.data.remote.ApiService
import com.example.data.repository.NetworkRepositoryImpl
import com.example.domain.repository.LocalRepository
import com.example.domain.repository.NetworkRepository
import com.example.domain.use_case.all.AllUseCase
import com.example.domain.use_case.local.GetLocalNewsUseCase
import com.example.domain.use_case.local.SaveNewUseCase
import com.example.domain.use_case.remote.GetRemoteNewsUseCase
import com.example.domain.use_case.remote.SearchRemoteNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @[Provides Singleton]
    fun provideOkClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @[Provides Singleton]
    fun provideApiService(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @[Provides Singleton]
    fun provideNetworkRepositoryImpl(
        apiService: ApiService
    ): NetworkRepository {
        return NetworkRepositoryImpl(apiService)
    }

    @[Provides Singleton]
    fun provideAllUseCase(
        networkRepository: NetworkRepository,
        localRepository: LocalRepository
    ): AllUseCase{
        return AllUseCase(
            getRemoteNewsUseCase = GetRemoteNewsUseCase(networkRepository),
            searchRemoteNewsUseCase = SearchRemoteNewsUseCase(networkRepository),
            saveNewUseCase = SaveNewUseCase(localRepository),
            getLocalNewsUseCase = GetLocalNewsUseCase(localRepository)
        )
    }
}