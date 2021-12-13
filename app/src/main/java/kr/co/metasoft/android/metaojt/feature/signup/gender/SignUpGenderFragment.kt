package kr.co.metasoft.android.metaojt.feature.signup.gender

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.gson.Gson
import kr.co.metasoft.android.metaojt.model.network.ApiRepository
import kr.co.metasoft.android.metaojt.model.UserModel
import kr.co.metasoft.android.metaojt.databinding.FragmentSignUpGenderBinding
import kr.co.metasoft.android.metaojt.global.EventObserver

class SignUpGenderFragment : Fragment() {

    private lateinit var binding: FragmentSignUpGenderBinding
    private lateinit var viewModel: SignUpGenderViewModel
    private lateinit var viewModelFactory: SignUpGenderViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = ApiRepository()
        viewModelFactory = SignUpGenderViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignUpGenderViewModel::class.java)

        binding = FragmentSignUpGenderBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        bindProgressButton(binding.btnSignupGenderContinue)

        val args: SignUpGenderFragmentArgs by navArgs()
        viewModel.userModel = Gson().fromJson(args.user, UserModel::class.java)
        Log.d("gentest", "${viewModel.userModel}")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigationToLoginEvent.observe(requireActivity(), EventObserver {
            val action = SignUpGenderFragmentDirections.actionSignUpGenderFragmentToLoginFragment()
            findNavController().navigate(action)
        })

        viewModel.navigationToBackEvent.observe(requireActivity(), EventObserver {
            findNavController().popBackStack()
        })

        viewModel.isBtnLoading.observe(requireActivity(), {
            if(it) {
                binding.btnSignupGenderContinue.showProgress {
                    progressColor = Color.WHITE
                }
            } else {
                binding.btnSignupGenderContinue.hideProgress("회원가입 완료하기")
            }
        })
    }
}