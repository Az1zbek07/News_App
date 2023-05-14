package com.example.newsapp.presentation.fragment.boards

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.User
import com.example.newsapp.R
import com.example.newsapp.adapter.ButtonAdapter
import com.example.newsapp.databinding.FragmentThirdBoardBinding
import com.example.newsapp.presentation.main.MainActivity
import com.example.newsapp.util.Constants
import com.example.newsapp.util.SharedPrefManager

class ThirdBoardFragment : Fragment(R.layout.fragment_third_board) {
    private var _binding: FragmentThirdBoardBinding? = null
    private val binding get() = _binding!!
    private val buttonAdapter by lazy { ButtonAdapter(requireContext()) }
    private val pref by lazy { SharedPrefManager(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentThirdBoardBinding.bind(view)

        binding.rv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = buttonAdapter
            suppressLayout(true)
        }

        buttonAdapter.submitList(Constants.setSplScrButtons())

        binding.btnNext.setOnClickListener {
            val user = User(isRegistered = true)
            pref.saveUser(user)

            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}