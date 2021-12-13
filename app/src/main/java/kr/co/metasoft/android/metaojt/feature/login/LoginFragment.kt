package kr.co.metasoft.android.metaojt.feature.login

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.gson.GsonBuilder
import kr.co.metasoft.android.metaojt.model.network.ApiRepository
import kr.co.metasoft.android.metaojt.model.UserModel
import kr.co.metasoft.android.metaojt.databinding.FragmentLoginBinding
import kr.co.metasoft.android.metaojt.global.EventObserver

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = ApiRepository()

        viewModelFactory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        bindProgressButton(binding.btnMainContinue)
        binding.btnMainContinue.attachTextChangeAnimator()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigationToLoginEvent.observe(requireActivity(), EventObserver {
            val action = LoginFragmentDirections.actionLoginFragmentToLoginPasswordFragment(viewModel.idText.value!!)
            findNavController().navigate(action)
        })

        viewModel.navigationToSignUpEvent.observe(requireActivity(), EventObserver {
            val gson = GsonBuilder().create()
            val userModel = UserModel(viewModel.idText.value!!)
            val userGson = gson.toJson(userModel, UserModel::class.java)
            Log.d("test", userGson)
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpPasswordFragment(userGson)
            findNavController().navigate(action)
        })

        viewModel.idText.observe(requireActivity(), {
            viewModel.setIdValidationText(viewModel.chkIdValidation(it))
        })

        viewModel.isBtnLoading.observe(requireActivity(), {
            if (it == true) {
                binding.btnMainContinue.showProgress {
                    progressColor = Color.WHITE
                }
            } else {
                binding.btnMainContinue.hideProgress("계속하기")
            }
        })
    }
}