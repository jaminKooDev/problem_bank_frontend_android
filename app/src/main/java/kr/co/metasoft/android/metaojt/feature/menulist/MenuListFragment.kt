package kr.co.metasoft.android.metaojt.feature.menulist

//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.fragment.findNavController
//import kr.co.metasoft.android.metaojt.databinding.FragmentMenuListBinding
//import kr.co.metasoft.android.metaojt.global.EventObserver
//import kr.co.metasoft.android.metaojt.model.network.ApiRepository
//
//class MenuListFragment : Fragment() {
//
//    private lateinit var binding: FragmentMenuListBinding
//    private lateinit var viewModel: MenuListViewModel
//    private lateinit var viewModelFactory: MenuListViewModelFactory
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val application = requireNotNull(this.activity).application
//        val repository = ApiRepository()
//
//        viewModelFactory = MenuListViewModelFactory(repository, application)
//        viewModel = ViewModelProvider(this, viewModelFactory).get(MenuListViewModel::class.java)
//
//        binding = FragmentMenuListBinding.inflate(inflater, container, false)
//        binding.lifecycleOwner = this
//        binding.viewModel = viewModel
//
//        viewModel.initTopMenuCode()
//        viewModel.initTreeMenuList()
//
//        return super.onCreateView(inflater, container, savedInstanceState)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel.navigationToBackEvent.observe(requireActivity(), EventObserver {
//            findNavController().popBackStack()
//        })
//    }
//}