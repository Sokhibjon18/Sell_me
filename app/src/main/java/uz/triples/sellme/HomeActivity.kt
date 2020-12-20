package uz.triples.sellme

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import uz.triples.sellme.login.SignInSharedVM
import uz.triples.sellme.utils.Helpers

class HomeActivity : AppCompatActivity() {

    private val signInViewModel: SignInSharedVM by viewModels()
    lateinit var facebookLogin: LoginButton
    private val callBackManager = CallbackManager.Factory.create()
    private val mAuth = FirebaseAuth.getInstance()
    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.d(TAG, "Name: " + mAuth.uid)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        signInViewModel.loginWithClicked().observe(this, Observer<Int> {
            if (it == 1) {
                facebookLogin.callOnClick()
                Log.d(TAG, "facebookClicked")
                signInViewModel.loginWith(-1)
            }else if (it == 0){
                val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
                val intent = mGoogleSignInClient.signInIntent
                startActivityForResult(intent, 0)
                signInViewModel.loginWith(-1)
            }
        })

        facebookLogin = LoginButton(this)
        facebookLogin.setReadPermissions(listOf("email", "public_profile"))
        facebookLogin.registerCallback(callBackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Log.d(TAG, "onSuccess: ")
                val graphRequest = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken()
                ) { `object`, _ ->
                    if (`object` != null) {
                        Log.d(TAG, "onActivityResult: $`object`")
                        val name = `object`.getString("first_name")
                        val surname = `object`.getString("last_name")
                        val email = `object`.has("email")
                        if (!email) {
                            signInToApp(name + surname, name + surname)
                        } else {
                            signInToApp(`object`.getString("email"), name + surname)
                        }
                    }
                }

                val arg = Bundle()
                arg.putString("fields", "id, first_name, last_name, email")

                graphRequest.parameters = arg
                graphRequest.executeAsync()
            }

            override fun onCancel() {
                Log.d(TAG, "onCancel: ")
            }

            override fun onError(error: FacebookException?) {
                Log.d(TAG, "onError: ")
            }

        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callBackManager.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            firebaseAuthWithGoogle(task.getResult(ApiException::class.java)!!.idToken!!)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }

    fun signInToApp(email: String, password: String){
        signInViewModel.signUpWithEmail("$email@sellMe.uz", password)
            .observe(this, {
                if (it != "Registration completed") {
                    signInViewModel.signInWithEmail(
                        "$email@sellMe.uz",
                        password
                    ).observe(this,{it2 ->
                        Helpers().toast(this, it2)
                    })
                } else {
                    Helpers().toast(this,"Registration completed")
                }
            })
    }
}