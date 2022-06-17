package kr.co.metasoft.android.metaojt.model.network

import kr.co.metasoft.android.metaojt.model.network.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private fun getClient(): Retrofit {
        val baseUrl = "http://backend.studyjobs.co.kr"

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