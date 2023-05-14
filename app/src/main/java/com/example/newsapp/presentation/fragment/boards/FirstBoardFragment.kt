package com.example.newsapp.presentation.fragment.boards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.SplashAdapter
import com.example.newsapp.databinding.FragmentFirstBoardBinding
import com.example.newsapp.util.Constants

class FirstBoardFragment : Fragment(R.layout.fragment_first_board) {
    private var _binding: FragmentFirstBoardBinding? = null
    private val binding get() = _binding!!
    private val splashAdapter by lazy { SplashAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBoardBinding.bind(view)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_firstBoardFragment_to_secondBoardFragment)
        }

        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = splashAdapter
        }

        splashAdapter.submitList(Constants.setSplScrImages())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}