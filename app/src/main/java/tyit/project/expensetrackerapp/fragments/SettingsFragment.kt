package tyit.project.expensetrackerapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import tyit.project.expensetrackerapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.FirebaseFirestore

import android.widget.EditText
import android.widget.Toast
import android.widget.Button

import tyit.project.expensetrackerapp.ExportData
import androidx.core.net.toUri

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class SettingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var auth: FirebaseAuth

    private lateinit var feedbackEditText: EditText

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exportDataCard = view.findViewById<MaterialCardView>(R.id.card_export_data)
        exportDataCard.setOnClickListener {
            val intent = Intent(requireActivity(), ExportData::class.java)
            startActivity(intent)
        }

        feedbackEditText = view.findViewById(R.id.feedback_input)

        view.findViewById<Button>(R.id.submit_feedback_button).setOnClickListener {
            submitFeedback()
        }

        // Add click listeners for social media links
        view.findViewById<LinearLayout>(R.id.gmail_layout).setOnClickListener {
            openGmail()
        }

        view.findViewById<LinearLayout>(R.id.instagram_layout).setOnClickListener {
            openUrl("https://www.instagram.com/aamin.0.0.0")
        }

        view.findViewById<LinearLayout>(R.id.telegram_layout).setOnClickListener {
            openUrl("https://t.me/useroftelegram000")
        }

        auth = FirebaseAuth.getInstance()

        view.findViewById<MaterialCardView>(R.id.card_about_us).setOnClickListener {
            showAboutUsDialog()
        }
    }

    private fun submitFeedback() {
        val feedback = feedbackEditText.text.toString()

        if (feedback.isNotEmpty()) {
            val feedbackData = hashMapOf(
                "feedback" to feedback,
                "timestamp" to System.currentTimeMillis()
            )

            val db = FirebaseFirestore.getInstance()
            db.collection("Feedbacks") // Create a collection named 'Feedbacks'
                .add(feedbackData)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Feedback submitted successfully!", Toast.LENGTH_SHORT).show()
                    feedbackEditText.text.clear()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext(), "Failed to submit feedback: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(requireContext(), "Feedback cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openGmail() {
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("aaminshamshadaliansari@gmail.com")) // Replace with your email address
            //putExtra(Intent.EXTRA_SUBJECT, "Feedback for Expense Tracker App") // Optional subject
            //putExtra(Intent.EXTRA_TEXT, "Hi, I would like to share my feedback...") // Optional body
        }

        try {
            startActivity(Intent.createChooser(emailIntent, "Send Email via"))
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle the case where no email app is installed
        }
    }

    private fun showAboutUsDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("About Us")
            .setMessage("This Expense Tracker app is designed to help users manage their finances effectively.")
            .setPositiveButton("OK", null)
            .show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}