package kr.co.metasoft.android.metaojt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kr.co.metasoft.android.metaojt.databinding.FragmentSignUpPasswordBinding

class SignUpPasswordFragment : Fragment() {

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

        return binding.root
    }
}