package com.uzuu.learn1_firebase.feature.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.uzuu.learn1_firebase.databinding.FragmentRegisterBinding
import com.uzuu.learn1_firebase.feature.auth.loginRegister.LoginFragmentDirections
import com.uzuu.learn1_firebase.feature.auth.loginRegister.LoginUiEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment: Fragment() {
    private var _binding : FragmentRegisterBinding ?= null

    val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupEvent()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { state ->
                    binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                    binding.tvError.text = state.error
                    binding.tvError.visibility = if (state.error != null) View.VISIBLE else View.GONE

                    // Disable button khi đang loading để tránh spam click
                    binding.btnRegister.isEnabled = !state.isLoading
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.channel.collect { e->
                    when(e) {
                        is RegisterUiEvent.navigateToHome -> {
                            val act = RegisterFragmentDirections.actionRegisterToHome()
                            findNavController().navigate(act)
                        }

                        is RegisterUiEvent.Toast -> {
                            Toast.makeText(context, e.msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect { e->
                    when(e) {
                        is RegisterUiEvent.navigateToHome -> {
                            val act = RegisterFragmentDirections.actionRegisterToHome()
                            findNavController().clearBackStack(act)
//                            findNavController().navigate(act)
                        }

                        is RegisterUiEvent.Toast -> {
                            Toast.makeText(context, e.msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupEvent() {
        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            viewModel.onRegister(email, password)
        }
    }
}