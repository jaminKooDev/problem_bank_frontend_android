package kr.co.metasoft.android.metaojt.feature.signup.phonenum

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.metasoft.android.metaojt.global.Event
import kr.co.metasoft.android.metaojt.global.ValidationPattern
import kr.co.metasoft.android.metaojt.model.UserModel

class SignUpPhoneNumViewModel : ViewModel() {

    lateinit var userModel: UserModel

    private val _navigationToSignUpGenderEvent = MutableLiveData<Event<Unit>>()
    val navigationToSignUpGenderEvent: LiveData<Event<Unit>> = _navigationToSignUpGenderEvent

    private val _navigationToBackEvent = MutableLiveData<Event<Unit>>()
    val navigationToBackEvent: LiveData<Event<Unit>> = _navigationToBackEvent

    val phoneNumText = MutableLiveData<String>()

    private val _isPhoneNumValidation = MutableLiveData<Boolean>()
    val isPhoneNumValidation: LiveData<Boolean> = _isPhoneNumValidation

    private val _phoneNumValidationText = MutableLiveData<String>()
    val phoneNumValidationText: LiveData<String> = _phoneNumValidationText
    fun setPhoneNumValidationText(value: String) {
        _phoneNumValidationText.value = value
    }

    fun onBackBtnClick(view: View) {
        _navigationToBackEvent.value = Event(Unit)
    }

    fun onContinueBtnClick(view: View) {
        _navigationToSignUpGenderEvent.value = Event(Unit)
    }

    fun chkPhoneNumValidation(value: String?) : String{
        if (value == null || value == "") {
            _isPhoneNumValidation.value = false
            return "핸드폰 번호를 입력해주세요."
        } else if (value.length < 9) {
            _isPhoneNumValidation.value = false
            return "핸드폰 번호는 10자 이상입니다."
        } else if (value.length > 12) {
            _isPhoneNumValidation.value = false
            return "핸드폰 번호는 12자를 넘길 수 없습니다."
        } else if (!Regex(ValidationPattern.PHONE_NUM_PATTERN).matches(value)) {
            _isPhoneNumValidation.value = false
            return "핸드폰 번호는 숫자만 입력이 가능합니다."
        }
        _isPhoneNumValidation.value = true
        return "올바른 형식입니다 :)"
    }
}