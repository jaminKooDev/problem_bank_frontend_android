package kr.co.metasoft.android.metaojt.api

import kr.co.metasoft.android.metaojt.NullOnEmptyConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private fun getClient(): Retrofit {
        val baseUrl = "http://192.168.0.200:20080"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(): ApiService {
        return getClient().create(ApiService::class.java)
    }

}