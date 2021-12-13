package kr.co.metasoft.android.metaojt.feature.signup.name

import android.os.Bundle
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
import kr.co.metasoft.android.metaojt.databinding.FragmentSignUpNameBinding
import kr.co.metasoft.android.metaojt.global.EventObserver

class SignUpNameFragment : Fragment() {

    private lateinit var binding: FragmentSignUpNameBinding
    private val viewModel: SignUpNameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpNameBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val args: SignUpNameFragmentArgs by navArgs()
        viewModel.userModel = Gson().fromJson(args.user, UserModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigationToBackEvent.observe(requireActivity(), EventObserver {
            findNavController().popBackStack()
        })

        viewModel.navigationToSignUpPhoneNumEvent.observe(requireActivity(), EventObserver {
            val gson = GsonBuilder().create()
            viewModel.userModel.name = viewModel.nameText.value
            val userGson = gson.toJson(viewModel.userModel, UserModel::class.java)
            val action = SignUpNameFragmentDirections.actionSignUpNameFragmentToSignUpPhoneNumFragment(userGson)
            findNavController().navigate(action)
        })

        viewModel.nameText.observe(requireActivity(), {
            viewModel.setNameValidationText(viewModel.chkNameValidation(it))
        })
    }
}