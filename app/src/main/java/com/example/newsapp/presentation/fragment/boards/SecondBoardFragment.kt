package com.example.newsapp.presentation.fragment.boards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentFirstBoardBinding
import com.example.newsapp.databinding.FragmentSecondBoardBinding

class SecondBoardFragment : Fragment(R.layout.fragment_second_board) {
    private var _binding: FragmentSecondBoardBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSecondBoardBinding.bind(view)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_secondBoardFragment_to_thirdBoardFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}