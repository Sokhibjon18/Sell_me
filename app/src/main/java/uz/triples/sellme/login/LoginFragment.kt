package uz.triples.sellme.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import uz.triples.sellme.R
import uz.triples.sellme.utils.Helpers

class LoginFragment : Fragment(R.layout.fragment_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: SignInSharedVM by viewModels()

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

        signUp.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment)
        }
    }
}