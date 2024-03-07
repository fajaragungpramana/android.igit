package com.github.fajaragungpramana.igit.core.di

import android.content.Context
import androidx.room.Room
import com.github.fajaragungpramana.igit.core.BuildConfig
import com.github.fajaragungpramana.igit.core.data.local.sql.SqlDatabase
import com.github.fajaragungpramana.igit.core.data.remote.auth.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) builder.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })

        builder.addInterceptor(authInterceptor)

        return builder.build()
    }

    @Provides
    fun provideSqlDatabase(@ApplicationContext applicationContext: Context): SqlDatabase =
        Room.databaseBuilder(applicationContext, SqlDatabase::class.java, BuildConfig.SQL_DATABASE).build()

}