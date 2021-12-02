package kr.co.metasoft.android.metaojt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import kr.co.metasoft.android.metaojt.api.ApiRepository
import kr.co.metasoft.android.metaojt.databinding.FragmentSignUpGenderBinding

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

        return binding.root
    }
}