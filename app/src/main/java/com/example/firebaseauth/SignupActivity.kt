package com.example.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnSingup
import kotlinx.android.synthetic.main.activity_main.edtEmail
import kotlinx.android.synthetic.main.activity_main.edtPassword
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)
        initFirebase()

    }

    private fun initFirebase() {

        auth = Firebase.auth

        btnSingup.setOnClickListener {
            var email: String = edtEmail.text.toString()
            var password: String = edtPassword.text.toString()

            val firstName:String=edtFirstName.text.toString()
            val lastName:String=edtLastName.text.toString()
            val phoneNumber:String=edtPhoneNo.text.toString()



            createAccount(email, password,firstName,lastName,phoneNumber)


        }
    }

    private fun createAccount(email: String, password: String,firstName:String,lastName:String,phoneNumber:String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Success singup", Toast.LENGTH_LONG).show()

                    val userId = auth.currentUser?.uid


                    val database = Firebase.database

                    val ref=database.getReference("Users")

                    val userDB = ref.child(userId!!)


                    userDB.child("firstName").setValue(firstName)
                    userDB.child("lastName").setValue(lastName)
                    userDB.child("phoneNumber").setValue(phoneNumber)

                    userDB.child("userId").setValue(userId)


                    finish()




                } else {
                    Toast.makeText(this, "Faill Singup" + task.exception, Toast.LENGTH_LONG).show()

                }
            }
    }
}
