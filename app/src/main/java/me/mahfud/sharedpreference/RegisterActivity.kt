package me.mahfud.sharedpreference

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


        btRegister.setOnClickListener {
            doRegister()
        }

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
