package kr.co.metasoft.android.metaojt.feature.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.co.metasoft.android.metaojt.R
import kr.co.metasoft.android.metaojt.model.network.ApiRepository
import kr.co.metasoft.android.metaojt.databinding.FragmentDashboardBinding
import kr.co.metasoft.android.metaojt.global.EventObserver
import kr.co.metasoft.android.metaojt.global.Preferences

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: DashboardViewModel
    private lateinit var viewModelFactory: DashboardViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = ApiRepository()
        viewModelFactory = DashboardViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)

        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        webViewInit()
        binding.navbarDashboard.run {
            setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.menu_dashboard_home -> {
                        Log.d("t", "home")
                        true
                    }
                    R.id.menu_dashboard_forward -> {
                        if(binding.wvDashboard.canGoForward())
                            binding.wvDashboard.goForward()
                        false
                    }
                    R.id.menu_dashboard_back -> {
                        if(binding.wvDashboard.canGoBack())
                            binding.wvDashboard.goBack()
                        false
                    }
                    R.id.menu_dashboard_history -> {
                        binding.wvDashboard.loadUrl("http://192.168.0.200:20080/personal/learning-history")
                        false
                    }
                    R.id.menu_dashboard_settings -> {
                        Log.d("t", "settings")
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigationToLoginEvent.observe(requireActivity(), EventObserver {
            val action = DashboardFragmentDirections.actionDashboardFragmentToLoginFragment()
            findNavController().navigate(action)
        })
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewInit() {
        val token = Preferences.getToken(binding.root.context)
        viewModel.chkTokenValidation(token)
        binding.wvDashboard.settings.javaScriptEnabled = true
        binding.wvDashboard.settings.domStorageEnabled = true

        binding.wvDashboard.webViewClient = WebViewClient()
        binding.wvDashboard.webChromeClient = object: WebChromeClient() {
            override fun onConsoleMessage(message: String?, lineNumber: Int, sourceID: String?) {
                if (message!!.contains("Error: Network Error")) {
                    binding.wvDashboard.reload()
                }
            }
        }
        binding.wvDashboard.clearCache(true)
        binding.wvDashboard.loadUrl(BASE_URL)
        binding.wvDashboard.loadDataWithBaseURL(BASE_URL, "<script type='text/javascript'>localStorage.setItem('token', '$token');window.location.replace('$BASE_URL');</script>", "text/html", "utf-8", null)
    }

    companion object {
        const val BASE_URL: String = "http://192.168.0.200:20080"
    }
}