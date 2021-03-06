package kr.co.metasoft.android.metaojt.feature.signup.phonenum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kr.co.metasoft.android.metaojt.model.UserModel
import kr.co.metasoft.android.metaojt.databinding.FragmentSignUpPhoneNumBinding
import kr.co.metasoft.android.metaojt.feature.signup.gender.SignUpGenderViewModel
import kr.co.metasoft.android.metaojt.feature.signup.gender.SignUpGenderViewModelFactory
import kr.co.metasoft.android.metaojt.global.EventObserver
import kr.co.metasoft.android.metaojt.model.network.ApiRepository

class SignUpPhoneNumFragment : Fragment() {

    private lateinit var binding: FragmentSignUpPhoneNumBinding
    private lateinit var viewModel: SignUpPhoneNumViewModel
    private lateinit var viewModelFactory: SignUpPhoneNumViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val repository = ApiRepository()
        viewModelFactory = SignUpPhoneNumViewModelFactory(repository, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignUpPhoneNumViewModel::class.java)

        binding = FragmentSignUpPhoneNumBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val args: SignUpPhoneNumFragmentArgs by navArgs()
        viewModel.userModel = Gson().fromJson(args.user, UserModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigationToBackEvent.observe(requireActivity(), EventObserver {
            findNavController().popBackStack()
        })

        viewModel.navigationToSignUpPhoneNumAuthEvent.observe(requireActivity(), EventObserver {
            val gson = GsonBuilder().create()
            viewModel.userModel.phoneNum = viewModel.phoneNumText.value
            val userGson = gson.toJson(viewModel.userModel, UserModel::class.java)
            val action = SignUpPhoneNumFragmentDirections.actionSignUpPhoneNumFragmentToSignUpPhoneNumAuthFragment(userGson)
            findNavController().navigate(action)
        })

        viewModel.phoneNumText.observe(requireActivity()) {
            viewModel.setPhoneNumValidationText(viewModel.chkPhoneNumValidation(it))
        }
    }
}