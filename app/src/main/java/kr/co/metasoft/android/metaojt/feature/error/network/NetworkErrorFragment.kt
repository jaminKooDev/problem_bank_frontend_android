package kr.co.metasoft.android.metaojt.feature.error.network

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import kr.co.metasoft.android.metaojt.R
import kr.co.metasoft.android.metaojt.databinding.FragmentNetworkErrorBinding
import kr.co.metasoft.android.metaojt.databinding.FragmentSignUpNameBinding
import kr.co.metasoft.android.metaojt.feature.signup.name.SignUpNameFragmentArgs
import kr.co.metasoft.android.metaojt.feature.signup.name.SignUpNameViewModel
import kr.co.metasoft.android.metaojt.model.UserModel

class NetworkErrorFragment : Fragment() {

    private lateinit var binding: FragmentNetworkErrorBinding
    private val viewModel: NetworkErrorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNetworkErrorBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
//        binding.viewModel = viewModel

        return binding.root
    }

}