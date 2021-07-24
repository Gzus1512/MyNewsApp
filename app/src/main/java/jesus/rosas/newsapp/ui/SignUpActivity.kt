package jesus.rosas.newsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import jesus.rosas.newsapp.R
import jesus.rosas.newsapp.databinding.ActivitySignUpBinding
import jesus.rosas.newsapp.model.goToActivity
import jesus.rosas.newsapp.model.toast

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGoLogin.setOnClickListener {
            goToActivity<LoginActivity>{
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.buttonSignup.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            if (isValidEmailPassword(email, password)){
                signUpByEmail(email, password)
            } else{
                toast("Fill fields correctly")
            }
        }
    }

    private fun signUpByEmail(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    toast("Log In please")
                    goToActivity<LoginActivity>{
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                } else {
                    toast("Unexpected error")
                }
            }
    }

    private fun isValidEmailPassword(email: String, password: String): Boolean {
        return email.isNotEmpty() &&
                password.isNotEmpty() &&
                password == binding.editTextConfirmPassword.text.toString()
    }
}