package com.example.firebaseauth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFirebase()

    }

    private fun initFirebase() {

        auth = Firebase.auth

        btnSingup.setOnClickListener{
           var intent =Intent(this,SignupActivity::class.java)
            startActivity(intent)

        }
        btnSingin.setOnClickListener{

            var email:String =edtEmail.text.toString()
            var password:String=edtPassword.text.toString()

            signinAccount(email, password)
        }

    }

    private fun signinAccount(email: String,password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                task->
                if(task.isSuccessful){
                    Toast.makeText(this,"Sigin Successful"+auth.currentUser?.uid,Toast.LENGTH_LONG).show()



                    val intent =Intent(this,UserActivity::class.java)
                    startActivity(intent)



                }else{
                    Toast.makeText(this,"Sigin fail"+task.exception,Toast.LENGTH_LONG).show()
                }

        }
    }
}