package com.kaungmaw.marsgallery.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.kaungmaw.marsgallery.R
import com.kaungmaw.marsgallery.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment() : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val arguments =  DetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val factory = DetailViewModelFactory(arguments , application)
        val viewModel by viewModels<DetailViewModel>{factory}

        val binding: FragmentDetailBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)

        binding.viewModel = viewModel

        viewModel.detailPropertyText.observe(viewLifecycleOwner , Observer {
            binding.textView.text = it
        })

        return binding.root
    }

}
