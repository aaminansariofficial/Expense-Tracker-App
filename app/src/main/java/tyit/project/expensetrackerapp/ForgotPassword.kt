package tyit.project.expensetrackerapp

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val submitButton: Button = findViewById(R.id.forgotPassBtnId)
        val etEmail: EditText = findViewById(R.id.forgotPassEmailTextBoxId)

        submitButton.setOnClickListener {
            val email: String = etEmail.text.toString().trim{ it <= ' '}
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show()
            } else if (!isEmailValid(email)) {
                Toast.makeText(this, "Incorrect Email Format", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Please check your email! (Including Spam)", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, task.exception?.message ?: "An error occurred", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}