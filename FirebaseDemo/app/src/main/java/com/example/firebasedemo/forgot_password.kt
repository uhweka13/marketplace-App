package com.example.firebasedemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_forgot_password.*

class forgot_password : AppCompatActivity() {
    private lateinit var email: String
    private lateinit var npassword: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val et_email: EditText = findViewById(R.id.et_forgot_email)
        val et_npassword: EditText = findViewById(R.id.et_forgot_npassword)
        val bt_forgot_submit: Button = findViewById(R.id.bt_forgot_submit)

        val sharedPref = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE)

        val savedEmail = sharedPref.getString("email", "")
        val savedPassword = sharedPref.getString("password", "")

        if (savedEmail != "" && savedPassword != ""){

        val dashboard = Intent(this, MainActivity::class.java)
        startActivity(dashboard)
        finish()
    }

    bt_forgot_submit.setOnClickListener(View.OnClickListener{


        val et_email: String = et_forgot_email.text.toString().trim()
        val et_npassword: String = et_forgot_npassword.text.toString().trim()

        if (email.isEmpty()) {
            et_forgot_email.setError("Email is Empty")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_forgot_email.setError("Invalid Email")
        } else if (npassword.isEmpty()) {
            et_npassword.setError("Password is Empty")
        } else if (npassword.length < 6) {
            et_npassword.setError("Passwword too Short")
        } else {
            val dashboard = Intent(this, MainActivity::class.java)
            dashboard.putExtra("emaail", email)
            startActivity(dashboard)
            finish()

            Toast.makeText(this, "Login Succesful", Toast.LENGTH_LONG).show()
        }
    })
}




}

private fun String.setError(s: String) {

}
