package kr.co.metasoft.android.metaojt.feature.login.details

import android.graphics.Color
import android.os.Bundle
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
import kr.co.metasoft.android.metaojt.model.network.ApiRepository
import kr.co.metasoft.android.metaojt.databinding.FragmentLoginPasswordBinding
import kr.co.metasoft.android.metaojt.global.EventObserver

class LoginPasswordFragment : Fragment() {

    private lateinit var binding: FragmentLoginPasswordBinding
    private lateinit var viewModel: LoginPasswordViewModel
    private lateinit var viewModelFactory: LoginPasswordViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val repository = ApiRepository()
        viewModelFactory = LoginPasswordViewModelFactory(repository, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginPasswordViewModel::class.java)

        val args: LoginPasswordFragmentArgs by navArgs()
        viewModel.loginId.value = args.username

        binding = FragmentLoginPasswordBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        bindProgressButton(binding.btnLoginPasswordContinue)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigationToBackEvent.observe(requireActivity(), EventObserver {
            findNavController().popBackStack()
        })

        viewModel.navigationToDashboardEvent.observe(requireActivity(), EventObserver {
            val action = LoginPasswordFragmentDirections.actionLoginPasswordFragmentToDashboardFragment()
            findNavController().navigate(action)
        })

        viewModel.pwText.observe(requireActivity()) {
            viewModel.setPwValidationText(viewModel.chkPwValidation(it))
        }

        viewModel.isBtnLoading.observe(requireActivity()) {
            if (it) {
                binding.btnLoginPasswordContinue.showProgress {
                    progressColor = Color.WHITE
                }
            } else {
                binding.btnLoginPasswordContinue.hideProgress("로그인")
            }
        }

    }
}