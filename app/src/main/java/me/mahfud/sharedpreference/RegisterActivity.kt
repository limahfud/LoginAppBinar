package me.mahfud.sharedpreference

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    var name = ""
    var lahir = ""
    var age = ""
    var email = ""
    var password = ""
    var repeatPassword = ""
    var gender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        if (checkIsLoggedIn()) return

        btRegister.setOnClickListener {
            doRegister()
        }

        tvLinkLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun checkIsLoggedIn(): Boolean {
        val sharedPref = getSharedPreferences("file", Context.MODE_PRIVATE) ?: return false

        if (sharedPref.getBoolean("isLoggedIn", false)) {
            startActivity(Intent(this, MainActivity::class.java))
            return true
        }

        return false
    }

    private fun doRegister() {
        name = etNama.text.toString()
        lahir = etLahir.text.toString()
        age = etUmur.text.toString()
        email = etEmail.text.toString()
        password = etPassword.text.toString()
        repeatPassword = etRepeatPassword.text.toString()
        gender = if (rbMale.isChecked) "Male" else "Female"

        if (validateCompleted() && validatePassword()) {
            saveDataToSharedPref()

            checkDataSaved()
        }
    }

    private fun validateCompleted(): Boolean {
        if (name.isEmpty() ||
            lahir.isEmpty() ||
            age.isEmpty() ||
            email.isEmpty() ||
            password.isEmpty() ||
            repeatPassword.isEmpty() ||
            gender.isEmpty()) {

            Toast.makeText(this, "Ada bagian yang masih kosong? Harap dipenuhi", Toast.LENGTH_LONG)
                    .show();

            return false
        }

        return true
    }

    private fun validatePassword(): Boolean {
        if (password != repeatPassword) {
            Toast.makeText(this, "Ulangi password tidak sesuai", Toast.LENGTH_LONG)
                    .show()

            return false
        }

        return true
    }

    private fun saveDataToSharedPref() {

        val sharedPref = getSharedPreferences("file", Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("key-nama", name)
            putString("key-lahir", lahir)
            putString("key-umur", age)
            putString("key-email", email)
            putString("key-password", password)
            putString("key-gender", gender)

            apply()
        }

        Toast.makeText(this, "Selamat anda sudah ter-registrasi", Toast.LENGTH_LONG)
                .show()
    }

    private fun checkDataSaved() {
        val sharedPref = getSharedPreferences("file", Context.MODE_PRIVATE) ?: return

        Log.d("CHECK-HASIL", sharedPref.getString(getString(R.string.key_shared_pref_name), "-"))
        Log.d("CHECK-HASIL", sharedPref.getString("key-lahir", "-"))
        Log.d("CHECK-HASIL", sharedPref.getString("key-umur", "-"))
        Log.d("CHECK-HASIL", sharedPref.getString("key-email", "-"))
        Log.d("CHECK-HASIL", sharedPref.getString("key-password", "-"))
        Log.d("CHECK-HASIL", sharedPref.getString("key-gender", "-"))
    }


}
