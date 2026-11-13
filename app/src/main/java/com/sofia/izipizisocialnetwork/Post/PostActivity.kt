package com.sofia.izipizisocialnetwork.Post


import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.sofia.izipizisocialnetwork.HomeActivity
import com.sofia.izipizisocialnetwork.Models.Post
import com.sofia.izipizisocialnetwork.Models.User

import com.sofia.izipizisocialnetwork.databinding.ActivityPostBinding
import com.sofia.izipizisocialnetwork.utils.POST
import com.sofia.izipizisocialnetwork.utils.POST_FOLDER
import com.sofia.izipizisocialnetwork.utils.USER_NODE
import com.sofia.izipizisocialnetwork.utils.USER_PROFILE_FOLDER
import com.sofia.izipizisocialnetwork.utils.uploadImage

class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    var imageUrl:String?=null
    private val launcher= registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->
        uri?.let {
            uploadImage(uri, POST_FOLDER){
                url ->
                if (url!=null){
                    binding.selectImage.setImageURI(uri)
                    imageUrl=url
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener{
            startActivity(Intent(this@PostActivity, HomeActivity::class.java))
            finish()
        }

        binding.selectImage.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this@PostActivity, HomeActivity::class.java))
            finish()
        }

        binding.postButton.setOnClickListener{
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {

            var user=it.toObject<User>()!!

            val post: Post = Post(
                postUrl = imageUrl!!,
                caption = binding.caption.editText?.text.toString(),
                uid = Firebase.auth.currentUser!!.uid,
                time=System.currentTimeMillis().toString())

            Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post).addOnSuccessListener {
                    startActivity(Intent(this@PostActivity, HomeActivity::class.java))
                    finish()
                }
            }

            }

        }
    }

}