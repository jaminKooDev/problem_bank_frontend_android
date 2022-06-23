package kr.co.metasoft.android.metaojt.feature.error.network

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import kr.co.metasoft.android.metaojt.R
import kr.co.metasoft.android.metaojt.databinding.FragmentDashboardBinding
import kr.co.metasoft.android.metaojt.databinding.FragmentNetworkErrorBinding
import kr.co.metasoft.android.metaojt.databinding.FragmentSignUpNameBinding
import kr.co.metasoft.android.metaojt.feature.dashboard.DashboardViewModel
import kr.co.metasoft.android.metaojt.feature.dashboard.DashboardViewModelFactory
import kr.co.metasoft.android.metaojt.feature.signup.name.SignUpNameFragmentArgs
import kr.co.metasoft.android.metaojt.feature.signup.name.SignUpNameViewModel
import kr.co.metasoft.android.metaojt.global.EventObserver
import kr.co.metasoft.android.metaojt.model.UserModel
import kr.co.metasoft.android.metaojt.model.network.ApiRepository

class NetworkErrorFragment : Fragment() {

    private lateinit var binding: FragmentNetworkErrorBinding
    private lateinit var viewModel: NetworkErrorViewModel
    private lateinit var viewModelFactory: NetworkErrorViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val repository = ApiRepository()
        viewModelFactory = NetworkErrorViewModelFactory(repository, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NetworkErrorViewModel::class.java)

        binding = FragmentNetworkErrorBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.toWebEvent.observe(requireActivity(), EventObserver {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.studyjobs.co.kr"))
            startActivity(intent)
        })

        viewModel.navigationToDashboardEvent.observe(requireActivity(), EventObserver {

        })
    }
}