package me.mahfud.sharedpreference

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("file", Context.MODE_PRIVATE) ?: return

        val emailFromPref = sharedPref.getString("key-email", "-")
        val namaFromPref = sharedPref.getString(getString(R.string.key_shared_pref_name), "-")
        val umurFromPref = sharedPref.getString("key-umur", "-")
        val lahirFromPref = sharedPref.getString("key-lahir", "-")
        val genderFromPref = sharedPref.getString("key-gender", "-")

        tvNama.text = namaFromPref
        tvEmail.text = emailFromPref
        tvGender.text = genderFromPref
        tvLahir.text = lahirFromPref
        tvUmur.text = umurFromPref

        tvLogout.setOnClickListener {
            logout()
        }
    }


    private fun logout() {
        val sharedPref = getSharedPreferences("file", Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("key-nama", "")
            putString("key-lahir", "")
            putString("key-umur", "")
            putString("key-email", "")
            putString("key-password", "")
            putString("key-gender", "")
            putBoolean("isLoggedIn", false)

            apply()
        }

        Toast.makeText(this, "Selamat anda sudah ter-logout", Toast.LENGTH_LONG)
                .show()

        val intent = Intent(this, RegisterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}
