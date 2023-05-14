package com.example.newsapp.presentation.fragment.saved

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.LocalAdapter
import com.example.newsapp.databinding.FragmentSavedBinding
import com.example.newsapp.presentation.fragment.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.fragment_saved) {
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!
    private val localAdapter by lazy { LocalAdapter() }
    private val viewModel: SavedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSavedBinding.bind(view)

        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = localAdapter
        }

        localAdapter.onClick = {
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            val bundle = bundleOf("inform" to it)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        viewModel.getLocalNews()

        lifecycleScope.launch{
            viewModel.state.collect{state ->
                when(state){
                    is com.example.newsapp.presentation.fragment.saved.SavedState.Empty -> {
                        binding.textView.isVisible = true
                        binding.btnFab.isVisible = true
                        binding.rv.isVisible = false
                    }

                    is com.example.newsapp.presentation.fragment.saved.SavedState.Loading -> {
                        binding.textView.isVisible = true
                        binding.btnFab.isVisible = true
                        binding.rv.isVisible = false
                    }

                    is com.example.newsapp.presentation.fragment.saved.SavedState.Error -> {
                        binding.textView.isVisible = true
                        binding.btnFab.isVisible = true
                        binding.rv.isVisible = false

                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }

                    is com.example.newsapp.presentation.fragment.saved.SavedState.Success -> {
                        if (state.informList.isNotEmpty()){
                            binding.textView.isVisible = false
                            binding.btnFab.isVisible = false
                            binding.rv.isVisible = true
                        }else{
                            binding.textView.isVisible = true
                            binding.btnFab.isVisible = true
                            binding.rv.isVisible = false

                        }

                        localAdapter.submitList(state.informList)
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}