package com.antonelli.nimble.di

import com.antonelli.nimble.api.ApiService
import com.antonelli.nimble.repository.HomeRepository
import com.antonelli.nimble.repository.HomeRepositoryImp
import com.antonelli.nimble.repository.LogInRepository
import com.antonelli.nimble.repository.LogInRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = "https://nimble-survey-web-mock.fly.dev/".toHttpUrl()

    // TODO cambiar url a produccion. Actualmente solo funciona esta

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: HttpUrl): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun providesLoginRepo(remoteService: ApiService): LogInRepository {
        return LogInRepositoryImp(remoteService)
    }

    @Provides
    fun providesHomeRepo(remoteService: ApiService): HomeRepository {
        return HomeRepositoryImp(remoteService)
    }
}
