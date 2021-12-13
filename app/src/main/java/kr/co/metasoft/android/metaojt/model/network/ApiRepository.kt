package kr.co.metasoft.android.metaojt.model.network

import kr.co.metasoft.android.metaojt.model.PersonModel
import kr.co.metasoft.android.metaojt.model.SignUpModel
import kr.co.metasoft.android.metaojt.model.UserModel
import kr.co.metasoft.android.metaojt.model.UserRequestModel
import retrofit2.Response

class ApiRepository {

    suspend fun postIdExists(id: String) : Response<String> {
        val userModel = UserRequestModel(id)
        return RetrofitClient.getApiService().postIdExists(userModel)
    }

    suspend fun postLogin(id: String, pw: String) : Response<*> {
        val userModel = UserRequestModel(id, pw)
        return RetrofitClient.getApiService().postLogin(userModel)
    }

    suspend fun postSignUp(userDto: UserModel, personDto: PersonModel) : Response<*> {
        val userTypeCode: Number = 2
        val signUpModel = SignUpModel(userDto, personDto, userTypeCode)
        return RetrofitClient.getApiService().postSignUp(signUpModel)
    }

    suspend fun getTokenValidation(token: String) : Response<*> {
        return RetrofitClient.getApiService().getTokenValidation(token)
    }
}