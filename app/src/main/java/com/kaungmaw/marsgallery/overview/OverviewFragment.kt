package com.kaungmaw.marsgallery.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kaungmaw.marsgallery.R
import com.kaungmaw.marsgallery.databinding.FragmentOverviewBinding
import com.kaungmaw.marsgallery.network.CameraFilter

/**
 * A simple [Fragment] subclass.
 */
class OverviewFragment : Fragment() {

    private val viewModel by viewModels<OverviewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)

        binding.rvGalleryList.adapter = OverviewAdapter(OverviewAdapter.OnItemClickListener {
            viewModel.executeDetailNavigation(it)
        })

        viewModel.navigateToDetail.observe( viewLifecycleOwner , Observer {
            it?.let {
                findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                viewModel.doneExecuteDetailNavigation()
            }
        })

        binding.viewModel = viewModel

        viewModel.status.observe(viewLifecycleOwner , Observer {
            binding.networkStatus = it
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.executeFilterCamera(
            when(item.itemId){
                R.id.mi_fhaz -> CameraFilter.FHAZ
                R.id.mi_mast -> CameraFilter.MAST
                R.id.mi_nav -> CameraFilter.NAVCAM
                else -> CameraFilter.ALL
            }
        )
        return true
    }
}


