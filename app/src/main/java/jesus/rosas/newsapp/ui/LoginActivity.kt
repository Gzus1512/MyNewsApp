package jesus.rosas.newsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import jesus.rosas.newsapp.MainActivity
import jesus.rosas.newsapp.R
import jesus.rosas.newsapp.databinding.ActivityLoginBinding
import jesus.rosas.newsapp.model.goToActivity
import jesus.rosas.newsapp.model.toast

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private lateinit var binding: ActivityLoginBinding
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val mGoogleApiClient: GoogleApiClient by lazy { getGoogleApiClient() }
    private val RC_GOOGLE_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (mAuth.currentUser == null){
            toast("Is NOT logged")
        } else {
            toast("IS logged")
            goToActivity<MainActivity>{
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            mAuth.signOut()
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            if (isValidEmailPassword(email, password)){
                logInByEmail(email, password)
            }
        }

        binding.buttonSingUp.setOnClickListener {
            goToActivity<SignUpActivity>()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.buttonGoogleLogin.setOnClickListener {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
        }
    }

    private fun logInByEmail(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->
            if (task.isSuccessful){
                toast("User is logged")
                goToActivity<MainActivity>{
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
            } else {
                toast("Error, try again")
            }
        }
    }

    private fun isValidEmailPassword(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun getGoogleApiClient(): GoogleApiClient{
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()
    }

    private fun loginByGoogleAccountIntoFirebase(googleAccount: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(googleAccount.idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener(this) {
            toast("Signed in with Google")
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        toast("Connection failed")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val account = result.signInAccount
                loginByGoogleAccountIntoFirebase(account!!)
            } else {
              toast("Error")
            }
        }
    }
}