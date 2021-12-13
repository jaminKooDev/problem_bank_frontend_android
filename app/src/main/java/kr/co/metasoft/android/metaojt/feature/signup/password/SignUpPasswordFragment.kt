package kr.co.metasoft.android.metaojt.feature.signup.password

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kr.co.metasoft.android.metaojt.model.UserModel
import kr.co.metasoft.android.metaojt.databinding.FragmentSignUpPasswordBinding
import kr.co.metasoft.android.metaojt.global.EventObserver

class SignUpPasswordFragment : Fragment() {

    val TAG = "supf"

    private lateinit var binding: FragmentSignUpPasswordBinding
    private val viewModel: SignUpPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpPasswordBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val args: SignUpPasswordFragmentArgs by navArgs()
        viewModel.userModel = Gson().fromJson(args.user, UserModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigationToBackEvent.observe(requireActivity(), EventObserver {
            findNavController().popBackStack()
        })

        viewModel.navigationToSignUpNameEvent.observe(requireActivity(), EventObserver {
            val gson = GsonBuilder().create()
            viewModel.userModel.password = viewModel.pwText.value
            val userGson = gson.toJson(viewModel.userModel, UserModel::class.java)
            val action =
                SignUpPasswordFragmentDirections.actionSignUpPasswordFragmentToSignUpNameFragment(userGson)
            findNavController().navigate(action)
        })

        viewModel.pwText.observe(requireActivity(), {
//            binding.tvSignUpPasswordValidation.text = viewModel.chkPwValidation(it)
            viewModel.setPwValidationText(viewModel.chkPwValidation(it))
            Log.d(TAG, "pwtxt: $it")
        })
        viewModel.pwChkText.observe(requireActivity(), {
//            binding.tvSignUpPasswordValidation2.text = viewModel.chkPwChkValidation(it)
            viewModel.setPwChkValidationText(viewModel.chkPwChkValidation(it))
            Log.d(TAG, "pwchktxt: $it")
        })
    }
}