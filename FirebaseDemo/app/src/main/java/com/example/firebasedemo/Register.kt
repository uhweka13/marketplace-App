package com.example.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    private lateinit var names: String
    private lateinit var email: String
    private lateinit var phone:String
    private lateinit var password: String
    private lateinit var address: String
    private var mAuth: FirebaseAuth? =null
    private var mDatabase:FirebaseDatabase? = null
    private lateinit var hud: KProgressHUD

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()

        mDatabase = FirebaseDatabase.getInstance()
        val mUsers: DatabaseReference = mDatabase!!.reference!!.child("Users")

        val login:TextView = findViewById(R.id.tv_already_reg)
        val et_name: EditText = findViewById(R.id.et_reg_Name)
        val et_email: EditText = findViewById(R.id.et_reg_email)
        val et_phone: EditText = findViewById(R.id.et_reg_phone)
        val et_password: EditText = findViewById(R.id.et_reg_password)
        val et_address: EditText = findViewById(R.id.et_reg_address)



        hud= KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel("Please wait")
            .setDetailsLabel("Creating User")
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)

        login.setOnClickListener(View.OnClickListener {
            val toLogin = Intent( this, Login::class.java)
            startActivity(toLogin)
            finish()
        })

        bt_reg.setOnClickListener(View.OnClickListener {
            names = et_name.text.toString().trim()
            email = et_email.text.toString().trim()
            phone = et_phone.text.toString().trim()
            password = et_password.text.toString().trim()
            address = et_address.text.toString().trim()

            if (names.isEmpty()){
                et_name.setError("Name is empty")
            } else if (email.isEmpty()){
                et_email.setError("email is empty")
            } else if (!email.contains("@")|| !email.contains(".")){
                et_email.setError("Invalid email")
            } else if (phone.isEmpty()|| !phone.startsWith("0")){
                et_phone.setError("Incorrect or empty phone No.")
            } else if (password.isEmpty()){
                et_password.setError("Password is Empty")

            }else if (address.isEmpty()){
                et_address.setError("Address is Empty")
            } else{
                hud.show()
                mAuth!!.createUserWithEmailAndPassword(email,password)

                    .addOnCompleteListener(this){
                        task ->
                        hud.dismiss()

                        if (task.isSuccessful){

                            val userId =mAuth!!.currentUser!!.uid

                            //update user profile information
                            val currentUserDB = mUsers!!.child(userId)
                            currentUserDB.child("names").setValue(names)
                            currentUserDB.child("phone").setValue(phone)
                            currentUserDB.child("email").setValue(email)
                            currentUserDB.child("address").setValue(address)

                            updateUserInfoAndUI()

                        } else{

                            Toast.makeText(this,"Authentication Failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        })




    }

    private fun updateUserInfoAndUI() {
        //start next activity
        val intent = Intent(this, Login::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    fun userInput(){
        val name = findViewById<EditText>(R.id.et_reg_Name).text.toString();
        val email = findViewById<EditText>(R.id.et_reg_email).text.toString();
        val phone = findViewById<EditText>(R.id.et_reg_phone).text.toString()
        val password = findViewById<EditText>(R.id.et_reg_password).text.toString();
        val address = findViewById<EditText>(R.id.et_reg_address).text.toString()

        validate(name,email, password, address)
    }
    fun validate(name:String,email:String, password:String, address:String) {
        var msg = "";
        if (name.trim() == "") {
            msg = "Fill in your name";
        } else if (email.trim() == "") {
            msg = "Fill in your email"
        } else if (!email.contains("@") || !email.contains(".")) {
            et_reg_email.setError("invalid email")
        } else if (password.trim() == "") {
            msg = "Fill in your password"
        } else if (address.trim() ==""){
            msg = "Fill in Your Address"
        }
    }

    /*val user_name:EditText = findViewById(R.id.et_reg_Name)
    val user_email:EditText = findViewById(R.id.et_reg_email)
    val user_phone:EditText = findViewById(R.id.et_reg_phone)
    val user_password: EditText = findViewById(R.id.et_reg_password)
    val submit_bt: Button = findViewById(R.id.bt_reg)*/

}
