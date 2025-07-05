package tyit.project.expensetrackerapp

import android.view.View
import androidx.fragment.app.Fragment
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import tyit.project.expensetrackerapp.databinding.ActivityMainBinding
import tyit.project.expensetrackerapp.fragments.TransactionFragment
import tyit.project.expensetrackerapp.fragments.AccountFragment
import tyit.project.expensetrackerapp.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if(ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    101
                )
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transactionFragment = TransactionFragment()
        val accountFragment = AccountFragment()
        val settingsFragment = SettingsFragment()

        binding.chipAppBar.setItemSelected(R.id.nav_transaction, true)
        makeCurrentFragment(transactionFragment)
        binding.chipAppBar.setOnItemSelectedListener {
            when(it) {
                R.id.nav_transaction -> makeCurrentFragment(transactionFragment)
                R.id.nav_account -> makeCurrentFragment(accountFragment)
                R.id.nav_settings -> makeCurrentFragment(settingsFragment)
            }
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
        when(fragment) {
            is TransactionFragment -> binding.floatingButton.show()
            is SettingsFragment, is AccountFragment -> binding.floatingButton.hide()
        }
    }

    fun onFloatingButtonClick(view: View) {
        val intent = Intent(this, InsertionActivity::class.java)
        startActivity(intent)
    }
}