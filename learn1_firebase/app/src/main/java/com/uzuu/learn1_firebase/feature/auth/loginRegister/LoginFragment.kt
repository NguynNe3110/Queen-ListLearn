package com.uzuu.learn1_firebase.feature.auth.loginRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.uzuu.learn1_firebase.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(){
    private var _binding : FragmentLoginBinding ?= null

    val binding get() = _binding!!

    private val viewModel: LoginRegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupEvent()
        setOnChanged()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { state ->
                    binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                    binding.tvError.text = state.error
                    binding.tvError.visibility = if (state.error != null) View.VISIBLE else View.GONE

                    // Disable button khi đang loading để tránh spam click
                    binding.btnLogin.isEnabled = !state.isLoading
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEventChannel.collect { e->
                    when(e) {
                        is LoginUiEvent.NavigateToHome -> {
                            val act = LoginFragmentDirections.actionLoginToHome()
                            findNavController().navigate(act)
                        }
                        is LoginUiEvent.NavigateToRegister -> {
                            val act = LoginFragmentDirections.actionLoginToRegister()
                            findNavController().navigate(act)
                        }
                        is LoginUiEvent.Toast -> {
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
                        is LoginUiEvent.NavigateToHome -> {
                            val act = LoginFragmentDirections.actionLoginToHome()
                            findNavController().navigate(act)
                        }
                        is LoginUiEvent.NavigateToRegister -> {
                            val act = LoginFragmentDirections.actionLoginToRegister()
                            findNavController().navigate(act)
                        }
                        is LoginUiEvent.Toast -> {
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
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.toString().trim()
            val password = binding.etPassword.toString().trim()

            viewModel.login(email, password)
        }

        binding.tvGoToRegister.setOnClickListener {
            viewModel.register()
        }
    }

    private fun setOnChanged(){
        binding.etEmail.addTextChangedListener { editale ->
            viewModel.onEmailChanged(editale.toString() ?: "")
        }

        binding.etPassword.addTextChangedListener { editable ->
            viewModel.onPasswordChanged(editable.toString() ?: "")
        }
    }
}