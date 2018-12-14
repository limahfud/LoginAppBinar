package me.mahfud.sharedpreference

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            tryLogin(email, password)

            // joko@gmail.com
        }

    }

    private fun tryLogin(email: String, password: String) {


        val sharedPref = getSharedPreferences("file", Context.MODE_PRIVATE) ?: return

        val emailFromPref = sharedPref.getString("key-email", "-")
        val passwordFromPref = sharedPref.getString("key-password", "-")

        Log.d("TEST-EMAIL", "Email from Preference : $emailFromPref , Email Input: $email")
        Log.d("TEST-EMAIL", "Password from Preference : $passwordFromPref , Password Input: $password")

        if (emailFromPref == email && passwordFromPref == password) {
            with (sharedPref.edit()) {
                putBoolean("isLoggedIn", true)
                apply()
            }
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Maaf anda gagal login", Toast.LENGTH_SHORT).show()

        }
    }

}
