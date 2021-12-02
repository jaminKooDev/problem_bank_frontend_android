package kr.co.metasoft.android.metaojt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kr.co.metasoft.android.metaojt.databinding.FragmentSignUpNameBinding

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
        return binding.root
    }
}