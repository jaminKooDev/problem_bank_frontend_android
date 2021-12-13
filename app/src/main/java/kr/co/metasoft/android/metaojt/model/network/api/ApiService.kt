package kr.co.metasoft.android.metaojt.model.network.api

import kr.co.metasoft.android.metaojt.model.SignUpModel
import kr.co.metasoft.android.metaojt.model.UserRequestModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

//    @FormUrlEncoded
    @POST("/api/common/users/id-exists")
    suspend fun postIdExists(
        @Body userModel: UserRequestModel
    ): Response<String>

    @POST("api/login")
    suspend fun postLogin(
        @Body userModel: UserRequestModel
    ) : Response<*>

    @POST("/api/app/accounts/sign-up")
    suspend fun postSignUp(
        @Body signUpModel: SignUpModel
    ) : Response<*>

    @GET("/api")
    suspend fun getTokenValidation(
        @Header("Authorization") token: String
    ) : Response<*>
}