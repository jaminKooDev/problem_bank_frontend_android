package kr.co.metasoft.android.metaojt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kr.co.metasoft.android.metaojt.databinding.FragmentSignUpPhoneNumBinding

class SignUpPhoneNumFragment : Fragment() {

    private lateinit var binding: FragmentSignUpPhoneNumBinding
    private val viewModel: SignUpPhoneNumViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpPhoneNumBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}