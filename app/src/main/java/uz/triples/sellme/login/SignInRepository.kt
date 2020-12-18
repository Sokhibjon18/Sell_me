package uz.triples.sellme.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class SignInRepository(private val application: Application) {

    private val TAG = "LoginRepository"
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUpWithEmail(email: String, password: String): MutableLiveData<String> {
        val result = MutableLiveData<String>()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    result.value = "Registration completed"
                } else {
                    result.value = it.exception?.message.toString()
                }
            }
        return result
    }

    fun signInWithEmail(email: String, password: String): MutableLiveData<String> {
        val result = MutableLiveData<String>()
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful)
                result.value = "Welcome back"
            else
                result.value = it.exception?.message.toString()
        }
        return result
    }
}