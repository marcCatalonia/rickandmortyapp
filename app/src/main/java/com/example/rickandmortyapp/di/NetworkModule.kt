package com.example.rickandmortyapp.di

import com.example.rickandmortyapp.data.remote.RickyMortyAPI
import com.example.rickandmortyapp.data.repository.CharactersRepository
import com.example.rickandmortyapp.data.repository.CharactersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit): RickyMortyAPI{
        return retrofit.create(RickyMortyAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(api: RickyMortyAPI): CharactersRepository
    = CharactersRepositoryImpl(api)
}