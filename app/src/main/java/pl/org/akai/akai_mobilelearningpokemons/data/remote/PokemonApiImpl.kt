package pl.org.akai.akai_mobilelearningpokemons.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

fun getPokemonApi(): PokemonApi {
    return Retrofit.Builder()
        .baseUrl(PokemonApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
            .build()
        )
        .build()
        .create()
}