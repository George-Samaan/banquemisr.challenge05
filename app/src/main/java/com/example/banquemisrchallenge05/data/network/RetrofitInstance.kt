package com.example.banquemisrchallenge05.data.network

import com.example.banquemisrchallenge05.utils.Constants
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


object RetrofitInstance {
    private const val CACHE_SIZE = 10 * 1024 * 1024 // 10 MiB

    private val retrofit by lazy {
        val cacheDir = File("your_cache_directory_path")
        val cache = Cache(cacheDir, CACHE_SIZE.toLong())

        val client = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                val url = request.url.newBuilder()
                    .addQueryParameter("api_key", Constants.API_KEY)
                    .build()

                val newRequest = request.newBuilder()
                    .url(url)
                    .cacheControl(
                        CacheControl.Builder()
                            .maxAge(5, java.util.concurrent.TimeUnit.MINUTES)
                            .build()
                    )
                    .build()
                chain.proceed(newRequest)
            }
            .build()

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiServices: TMDBApiServices by lazy {
        retrofit.create(TMDBApiServices::class.java)
    }
}