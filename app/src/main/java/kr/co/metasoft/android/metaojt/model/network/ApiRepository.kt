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
        val signUpModel = SignUpModel(userDto, personDto)
        return RetrofitClient.getApiService().postSignUp(signUpModel)
    }

    suspend fun postLogout(token: String) : Response<*> {
        return RetrofitClient.getApiService().postLogout("Bearer $token")
    }

    suspend fun getTokenValidation(token: String) : Response<*> {
        return RetrofitClient.getApiService().getTokenValidation("Bearer $token")
    }

    suspend fun getCode(id: Number) : Response<*> {
        return RetrofitClient.getApiService().getCode(id)
    }

    suspend fun postAuth(userPhoneNumAuthModel: UserPhoneNumAuthModel): Response<UserPhoneNumAuthModel> {
        return RetrofitClient.getApiService().postAuth(userPhoneNumAuthModel)
    }

    suspend fun postAuthVerify(userPhoneNumAuthModel: UserPhoneNumAuthModel): Response<UserPhoneNumAuthModel> {
        return RetrofitClient.getApiService().postAuthVerify(userPhoneNumAuthModel)
    }
}