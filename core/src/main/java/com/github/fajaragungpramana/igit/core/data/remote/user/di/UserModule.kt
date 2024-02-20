package com.github.fajaragungpramana.igit.core.data.remote.di

import com.github.fajaragungpramana.igit.core.data.remote.user.IUserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    fun provideDataSource(retrofit: Retrofit): IUserDataSource =
        retrofit.create(IUserDataSource::class.java)

}