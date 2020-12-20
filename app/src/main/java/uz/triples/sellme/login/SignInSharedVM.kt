package uz.triples.sellme.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SignInSharedVM(application: Application) : AndroidViewModel(application) {

    private val TAG = "SignInSharedVM"
    private val repository = SignInRepository(application)
    private val loginWith = MutableLiveData<Int>()

    init {
        loginWith.value = -1
    }

    fun signUpWithEmail(email: String, password: String): MutableLiveData<String> {
        return repository.signUpWithEmail(email, password)
    }

    fun signInWithEmail(email: String, password: String): MutableLiveData<String> {
        return repository.signInWithEmail(email, password)
    }

    fun loginWithClicked(): LiveData<Int> {
        return loginWith
    }

    fun loginWith(type: Int){
        loginWith.value = type
        Log.d(TAG, "facebookClick: ")
    }
}