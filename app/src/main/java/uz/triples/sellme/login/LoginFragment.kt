package uz.triples.sellme.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import uz.triples.sellme.R
import uz.triples.sellme.utils.Helpers

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val TAG = "LoginFragment"
    private val viewModel: SignInSharedVM by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton.setOnClickListener {
            if (emailLogin.text.isNullOrEmpty()) {
                emailLogin.error = "Please fill the form"
                return@setOnClickListener
            }
            if (passwordLogin.text.isNullOrEmpty()) {
                passwordLogin.error = "Please fill the form"
                return@setOnClickListener
            } else if (passwordLogin.text.length < 6) {
                passwordLogin.error = "At least 6 character"
                return@setOnClickListener
            }

            viewModel.signInWithEmail(
                emailLogin.text.toString(),
                passwordLogin.text.toString()
            ).observe(viewLifecycleOwner, {
                Helpers().toast(requireActivity(), it)
            })
        }

        facebook.setOnClickListener {
            viewModel.loginWith(1)
        }

        google.setOnClickListener {
            viewModel.loginWith(0)
        }

        signUp.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment)
        }

        message.setOnClickListener {
            Helpers().toast(requireActivity(),"You are currently entering with email!")
        }
    }
}