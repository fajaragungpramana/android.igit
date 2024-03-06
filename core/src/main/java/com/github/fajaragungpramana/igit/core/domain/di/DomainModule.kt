package com.github.fajaragungpramana.igit.core.domain.di

import com.github.fajaragungpramana.igit.core.data.local.cache.ICacheRepository
import com.github.fajaragungpramana.igit.core.data.local.sql.ISqlRepository
import com.github.fajaragungpramana.igit.core.data.remote.user.UserRepository
import com.github.fajaragungpramana.igit.core.domain.local.LocalInteractor
import com.github.fajaragungpramana.igit.core.domain.local.LocalUseCase
import com.github.fajaragungpramana.igit.core.domain.user.UserInteractor
import com.github.fajaragungpramana.igit.core.domain.user.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideUserUseCase(
        userRepository: UserRepository,
        sqlRepository: ISqlRepository
    ): UserUseCase = UserInteractor(userRepository, sqlRepository)

    @Provides
    fun provideLocalUseCase(
        sqlRepository: ISqlRepository,
        cacheRepository: ICacheRepository
    ): LocalUseCase = LocalInteractor(sqlRepository, cacheRepository)

}