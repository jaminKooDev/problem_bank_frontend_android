package kr.co.metasoft.android.metaojt.feature.signup.phonenum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import kr.co.metasoft.android.metaojt.databinding.FragmentSignUpPhoneNumAuthBinding
import kr.co.metasoft.android.metaojt.global.EventObserver
import kr.co.metasoft.android.metaojt.model.UserModel
import kr.co.metasoft.android.metaojt.model.network.ApiRepository

class SignUpPhoneNumAuthFragment : Fragment() {

    private lateinit var binding: FragmentSignUpPhoneNumAuthBinding
    private lateinit var viewModel: SignUpPhoneNumViewModel
    private lateinit var viewModelFactory: SignUpPhoneNumViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val repository = ApiRepository()
        viewModelFactory = SignUpPhoneNumViewModelFactory(repository, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignUpPhoneNumViewModel::class.java)

        binding = FragmentSignUpPhoneNumAuthBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val args: SignUpPhoneNumAuthFragmentArgs by navArgs()
        viewModel.userModel = Gson().fromJson(args.user, UserModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigationToBackEvent.observe(requireActivity(), EventObserver {
            findNavController().popBackStack()
        })

        viewModel.navigationToSignUpLoginEvent.observe(requireActivity(), EventObserver {
            val action = SignUpPhoneNumAuthFragmentDirections.actionSignUpPhoneNumAuthFragmentToLoginFragment()
            findNavController().navigate(action)
        })
0
        viewModel.phoneNumAuthText.observe(requireActivity()) {
            viewModel.setPhoneNumAuthValidationText(viewModel.chkPhoneNumAuthValidation(it))
        }
    }
}