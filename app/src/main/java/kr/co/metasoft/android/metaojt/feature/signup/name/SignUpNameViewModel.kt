package kr.co.metasoft.android.metaojt.feature.signup.name

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.metasoft.android.metaojt.global.Event
import kr.co.metasoft.android.metaojt.global.ValidationPattern
import kr.co.metasoft.android.metaojt.model.UserModel

class SignUpNameViewModel : ViewModel() {

    lateinit var userModel: UserModel

    private val _navigationToSignUpPhoneNumEvent = MutableLiveData<Event<Unit>>()
    val navigationToSignUpPhoneNumEvent: LiveData<Event<Unit>> = _navigationToSignUpPhoneNumEvent

    private val _navigationToBackEvent = MutableLiveData<Event<Unit>>()
    val navigationToBackEvent: LiveData<Event<Unit>> = _navigationToBackEvent

    val nameText = MutableLiveData<String>()

    private val _isNameValidation = MutableLiveData<Boolean>()
    val isNameValidation: LiveData<Boolean> = _isNameValidation

    private val _nameValidationText = MutableLiveData<String>()
    val nameValidationText: LiveData<String> = _nameValidationText
    fun setNameValidationText(value: String) {
        _nameValidationText.value = value
    }

    fun onBackBtnClick(view: View) {
        _navigationToBackEvent.value = Event(Unit)
    }

    fun onContinueBtnClick(view: View) {
        _navigationToSignUpPhoneNumEvent.value = Event(Unit)
    }

    fun chkNameValidation(value: String?) : String{
        if (value == null || value == "") {
            _isNameValidation.value = false
            return "이름을 입력해주세요."
        } else if (Regex(ValidationPattern.NAME_PATTERN).matches(value)) {
            _isNameValidation.value = false
            return "이름은 특수문자를 입력할 수 없습니다."
        }
        _isNameValidation.value = true
        return "올바른 형식입니다 :)"
    }



}