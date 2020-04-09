package com.example.ocadochallenge.repository.rest

import com.example.brewdogbeers.Constants.BASE_URL
import com.example.ocadochallenge.repository.rest.model.ProductClusterListNetworkModel
import com.example.ocadochallenge.repository.rest.model.ProductNetworkModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products")
    fun getProductList(): Call<ProductClusterListNetworkModel>

    @GET("product/{id}")
    fun getProduct(@Path("id") id: Int): Call<ProductNetworkModel>

    companion object {
        fun create(): ApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}