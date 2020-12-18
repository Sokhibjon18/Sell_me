package uz.triples.sellme.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SignInSharedVM(application: Application) : AndroidViewModel(application) {

    private val repository = SignInRepository(application)

    fun signUpWithEmail(email: String, password: String): MutableLiveData<String> {
        return repository.signUpWithEmail(email, password)
    }

    fun signInWithEmail(email: String, password: String): MutableLiveData<String> {
        return repository.signInWithEmail(email, password)
    }
}