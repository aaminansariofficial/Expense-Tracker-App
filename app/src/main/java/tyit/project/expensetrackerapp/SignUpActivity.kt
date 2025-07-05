package tyit.project.expensetrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import tyit.project.expensetrackerapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.showPasswordCheckBoxId.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.passwordTextBoxId.transformationMethod = null
                binding.confirmPasswordTextBoxId.transformationMethod = null
            } else {
                binding.passwordTextBoxId.transformationMethod = android.text.method.PasswordTransformationMethod.getInstance()
                binding.confirmPasswordTextBoxId.transformationMethod = android.text.method.PasswordTransformationMethod.getInstance()
            }
            binding.passwordTextBoxId.setSelection(binding.passwordTextBoxId.text.length)
            binding.confirmPasswordTextBoxId.setSelection(binding.confirmPasswordTextBoxId.text.length)
        }

        binding.alreadyHaveAccountTextId.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signUpBtnId.setOnClickListener {
            val email = binding.emailTextBoxId.text.toString()
            val pass = binding.passwordTextBoxId.text.toString()
            val confirmPass = binding.confirmPasswordTextBoxId.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (confirmPass == pass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            finish()
                        } else {
                            handleFirebaseSignupException(it.exception)
                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleFirebaseSignupException(exception: Exception?) {
        when (exception) {

            is FirebaseAuthWeakPasswordException -> {
                Toast.makeText(this, "Password is too weak. Please choose a stronger password.", Toast.LENGTH_SHORT).show()
            }

            is FirebaseAuthInvalidCredentialsException -> {
                Toast.makeText(this, "Invalid email format. Please enter a valid email address.", Toast.LENGTH_SHORT).show()
            }

            is FirebaseAuthUserCollisionException -> {
                Toast.makeText(this, "This email is already registered. Please log in or use a different email.", Toast.LENGTH_SHORT).show()
            }

            else -> {
                showToast("Error: ${exception?.localizedMessage ?: "Unknown error occurred"}")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}