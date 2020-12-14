package uz.triples.sellme.login

import android.app.Application
import com.google.firebase.auth.FirebaseAuth

class LoginRepository(application: Application) {

    private val TAG = "LoginRepository"
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUpWithEmail(email: String, password: String): Boolean {
        var isCompleted = false
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    isCompleted = true
                }
            }
        return isCompleted
    }
}