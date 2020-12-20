package uz.triples.sellme.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_registration.*
import uz.triples.sellme.R
import uz.triples.sellme.utils.Helpers

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var loading: AlertDialog
    private val viewModel: SignInSharedVM by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

            when {
                passwordRegistration.text.length<6 -> {
                    passwordRegistration.error = "At least 6 character!"
                }
                passwordConfirmRegistration.text.toString() != passwordRegistration.text.toString() -> {
                    Snackbar.make(registrationFragment, "Passwords are not matched", 5000).show()
                    passwordConfirmRegistration.error = "Didn't match"
                    return@setOnClickListener
                }
                else -> {
                    viewModel.signUpWithEmail(
                        emailRegistration.text.toString(),
                        passwordRegistration.text.toString()
                    ).observe(viewLifecycleOwner, {
                        Helpers().toast(requireActivity(), it)
                        loading.dismiss()
                    })
                    val loadingDialog = AlertDialog.Builder(requireContext())
                    loadingDialog.setView(
                        LayoutInflater
                            .from(requireContext())
                            .inflate(R.layout.progress_dialog, null, false)
                    )
                    loading = loadingDialog.create()
                    loading.show()
                }
            }
        }

        google.setOnClickListener {
            viewModel.loginWith(0)
        }

        facebook.setOnClickListener {
            viewModel.loginWith(1)
        }

        message.setOnClickListener {
            findNavController().popBackStack()
        }

        login.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}