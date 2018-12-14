package me.mahfud.sharedpreference

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

    }
}
