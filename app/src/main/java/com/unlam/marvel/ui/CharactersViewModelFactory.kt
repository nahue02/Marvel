package com.unlam.marvel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unlam.marvel.domain.PublicKeyInterceptor
import com.unlam.marvel.data.RetrofitCharactersRepository
import com.unlam.marvel.domain.CharactersService
import com.unlam.marvel.data.MarvelCharactersClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(PublicKeyInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiClient = retrofit.create(MarvelCharactersClient::class.java)

        val charactersApi = RetrofitCharactersRepository(apiClient)
        val charactersService = CharactersService(charactersApi)
        return CharactersViewModel(charactersService) as T
    }
}