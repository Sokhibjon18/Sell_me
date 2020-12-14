package uz.triples.sellme.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_registration.*
import uz.triples.sellme.R

class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: LoginSharedVM by viewModels()

        signUpButton.setOnClickListener {
            when {
                nameRegistration.text.isNullOrEmpty() -> {
                    nameRegistration.error = "Please fill name field"
                    return@setOnClickListener
                }
                emailRegistration.text.isNullOrEmpty() -> {
                    emailRegistration.error = "Please fill email field"
                    return@setOnClickListener
                }
                passwordRegistration.text.isNullOrEmpty() -> {
                    passwordRegistration.error = "Please fill password field"
                    return@setOnClickListener
                }
                passwordConfirmRegistration.text.isNullOrEmpty() -> {
                    passwordConfirmRegistration.error = "Please fill this field"
                    return@setOnClickListener
                }
            }

            if (passwordConfirmRegistration.text.toString() != passwordRegistration.text.toString()) {
                Snackbar.make(registrationFragment, "Passwords are not matched", 5000).show()
                passwordConfirmRegistration.error = "Didn't match"
                return@setOnClickListener
            } else {
                val signedIn = viewModel.signUpWithEmail(
                    emailRegistration.text.toString(),
                    passwordRegistration.text.toString()
                )
                if (!signedIn){
                    val toast = Toast(requireContext())
                }
            }
        }
    }
}