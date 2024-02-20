package com.github.fajaragungpramana.igit.core.data.remote.user.di

import com.github.fajaragungpramana.igit.core.data.remote.user.IUserDataSource
import com.github.fajaragungpramana.igit.core.data.remote.user.IUserRepository
import com.github.fajaragungpramana.igit.core.data.remote.user.UserRepository
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

    @Provides
    fun provideRepository(userDataSource: IUserDataSource): IUserRepository =
        UserRepository(userDataSource)

}