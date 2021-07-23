package jesus.rosas.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import jesus.rosas.newsapp.MainActivity
import jesus.rosas.newsapp.R
import jesus.rosas.newsapp.databinding.ActivityLoginBinding
import jesus.rosas.newsapp.databinding.ActivityMainBinding
import jesus.rosas.newsapp.model.goToActivity
import jesus.rosas.newsapp.model.toast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (mAuth.currentUser == null){
            toast("Is NOT logged")
        } else {
            toast("IS logged")
            goToActivity<MainActivity>()
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
    }

    private fun logInByEmail(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->
            if (task.isSuccessful){
                toast("User is logged")
            } else {
                toast("Error, try again")
            }
        }
    }

    private fun isValidEmailPassword(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }
}