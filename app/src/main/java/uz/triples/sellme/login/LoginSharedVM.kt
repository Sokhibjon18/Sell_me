package uz.triples.sellme.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class LoginSharedVM(application: Application) : AndroidViewModel(application) {

    private val repository = LoginRepository(application)

    fun signUpWithEmail(email: String, password: String): Boolean {
        return repository.signUpWithEmail(email, password)
    }
}