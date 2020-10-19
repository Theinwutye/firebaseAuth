package com.example.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var database:FirebaseDatabase
    lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        database= Firebase.database
        auth=Firebase.auth
        dbRef=database.getReference("Users").child(auth.currentUser!!.uid)

        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               // val value = snapshot.value
                var name ="${snapshot.child("firstName").value} ${snapshot.child("lastName").value}"


              //  val user = snapshot.getValue(User::class.java)

                    txtUser.text = name.toString()

            }
            override fun onCancelled(error: DatabaseError) {


            }

        })

    }
}