package com.example.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class change_password : AppCompatActivity() {
    private lateinit var new_password:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        val et_new_password= findViewById<EditText>(R.id.et_new_password)
        val bt_submit=findViewById<Button>(R.id.bt_submit)


        bt_submit.setOnClickListener(View.OnClickListener{
new_password = et_new_password.text.toString().trim()
            if (!new_password.isEmpty()){
                val user = FirebaseAuth.getInstance().currentUser

                user!!.updatePassword(new_password)
                    .addOnCompleteListener(){ task ->

                        if (task.isSuccessful){
                            Toast.makeText(this,"Password Updated Successful.", Toast.LENGTH_LONG)

                            updateUI()
                        }
                    }
            }

        })

    }

    private fun updateUI() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        startActivity(intent)
        finish()
    }

}
