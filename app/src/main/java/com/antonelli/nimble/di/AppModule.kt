package com.antonelli.nimble.di

import android.content.Context
import com.antonelli.nimble.api.ApiService
import com.antonelli.nimble.entity.LogInModel
import com.antonelli.nimble.repository.AuthRepository
import com.antonelli.nimble.repository.AuthRepositoryImpl
import com.antonelli.nimble.repository.HomeRepository
import com.antonelli.nimble.repository.HomeRepositoryImp
import com.antonelli.nimble.repository.LogInRepository
import com.antonelli.nimble.repository.LogInRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideBaseUrl() = "https://survey-api.nimblehq.co/api/v1/".toHttpUrl()

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

    @Singleton
    @Provides
    fun provideAuthRepository(@ApplicationContext context: Context): AuthRepository {
        return AuthRepositoryImpl(context)
    }

    @Provides
    fun providesLoginRepo(
        loginModel: LogInModel,
        remoteService: ApiService,
        authRepository: AuthRepository
    ): LogInRepository {
        return LogInRepositoryImp(loginModel, remoteService, authRepository)
    }

    @Provides
    fun providesHomeRepo(
        remoteService: ApiService,
        authRepository: AuthRepository,
        logInRepository: LogInRepository
    ): HomeRepository {
        return HomeRepositoryImp(remoteService, authRepository, logInRepository)
    }

    @Provides
    fun providesLogInModel() = LogInModel()
}
