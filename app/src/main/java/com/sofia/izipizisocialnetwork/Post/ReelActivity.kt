package com.sofia.izipizisocialnetwork.Post

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.sofia.izipizisocialnetwork.HomeActivity
import com.sofia.izipizisocialnetwork.Models.Reel
import com.sofia.izipizisocialnetwork.Models.User
import com.sofia.izipizisocialnetwork.databinding.ActivityReelBinding
import com.sofia.izipizisocialnetwork.utils.REEL
import com.sofia.izipizisocialnetwork.utils.REEL_FOLDER
import com.sofia.izipizisocialnetwork.utils.USER_NODE
import com.sofia.izipizisocialnetwork.utils.uploadVideo

class ReelActivity : AppCompatActivity() {

    val binding by lazy {
    ActivityReelBinding.inflate(layoutInflater)
    }
    private lateinit var imageUrl:String
    lateinit var progressDialog: ProgressDialog
    private val launcher= registerForActivityResult(ActivityResultContracts.GetContent()){
            uri->
        uri?.let {
            uploadVideo(uri, REEL_FOLDER, progressDialog ){
                    url ->
                if (url!=null){

                    imageUrl=url
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        progressDialog=ProgressDialog(this)

        binding.selectReel.setOnClickListener {
            launcher.launch("video/*")
        }

        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this@ReelActivity, HomeActivity::class.java))
            finish()
        }

        binding.postButton.setOnClickListener{
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {

                var user: User =it.toObject<User>()!!
                val reel: Reel = Reel(imageUrl!!, binding.caption.editText?.text.toString(),user.image!!)

                Firebase.firestore.collection(REEL).document().set(reel).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).document().set(reel)
                        .addOnSuccessListener {
                        startActivity(Intent(this@ReelActivity, HomeActivity::class.java))
                        finish()
                    }
                }
            }
        }

    }
}