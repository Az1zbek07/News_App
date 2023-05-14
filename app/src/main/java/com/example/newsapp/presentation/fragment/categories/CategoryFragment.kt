package com.example.newsapp.presentation.fragment.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.ButtonAdapter
import com.example.newsapp.databinding.FragmentCategoryBinding
import com.example.newsapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private val buttonAdapter by lazy { ButtonAdapter(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCategoryBinding.bind(view)

        binding.rv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter =  buttonAdapter
            suppressLayout(true)
        }

        buttonAdapter.submitList(Constants.setSplScrButtons())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}