package kr.co.metasoft.android.metaojt.feature.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kr.co.metasoft.android.metaojt.global.Event
import kr.co.metasoft.android.metaojt.global.ValidationPattern
import kr.co.metasoft.android.metaojt.model.network.ApiRepository
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
     private val repository: ApiRepository
): ViewModel() {

     val idText = MutableLiveData<String>()

     private val _idValidationText = MutableLiveData<String>()
     val idValidationText: LiveData<String> = _idValidationText
     fun setIdValidationText(value: String) {
          _idValidationText.value = value
     }

     private val _isIdValidation = MutableLiveData<Boolean>()
     val isIdValidation: LiveData<Boolean> = _isIdValidation

     private val parentJob = Job()
     private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
     private val scope = CoroutineScope(coroutineContext)

     private val _navigationToLoginEvent = MutableLiveData<Event<Unit>>()
     val navigationToLoginEvent: LiveData<Event<Unit>> = _navigationToLoginEvent

     private val _navigationToSignUpEvent = MutableLiveData<Event<Unit>>()
     val navigationToSignUpEvent: LiveData<Event<Unit>> = _navigationToSignUpEvent

     private val _isBtnLoading = MutableLiveData<Boolean>()
     val isBtnLoading: LiveData<Boolean> = _isBtnLoading


     fun onContinueBtnClick(view: View?) {
          scope.launch(Dispatchers.Main) {
               try {
                    _isBtnLoading.postValue(true)
                    val response = repository.postIdExists(idText.value ?: "")

                    _isBtnLoading.postValue(false)
                    if ((response.body() ?: "0").toInt() == 1)
                         _navigationToLoginEvent.value = Event(Unit)
                    else _navigationToSignUpEvent.value = Event(Unit)
               } catch (e: Exception) { }
          }
     }


     fun chkIdValidation(value: String?): String {
          if (value == null || value == "") {
               _isIdValidation.value = false
               return "???????????? ??????????????????."
          } else if (value.length < 4) {
               _isIdValidation.value = false
               return "???????????? 4?????? ???????????????."
          } else if (value.length > 20) {
               _isIdValidation.value = false
               return "???????????? 20????????? ?????? ??? ????????????."
          } else if (Regex(ValidationPattern.ID_ONLY_CHARACTER_AND_NUMBER_PATTERN).matches(value)) {
               _isIdValidation.value = false
               return "???????????? ??????????????? ????????? ????????? ??? ????????????."
          }
          _isIdValidation.value = true
          return "????????? ??????????????? :)"
     }

}