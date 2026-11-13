package com.sofia.izipizisocialnetwork.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.sofia.izipizisocialnetwork.Models.Post
import com.sofia.izipizisocialnetwork.Models.User
import com.sofia.izipizisocialnetwork.R
import com.sofia.izipizisocialnetwork.adapters.FollowAdapter
import com.sofia.izipizisocialnetwork.adapters.PostAdapter
import com.sofia.izipizisocialnetwork.databinding.FragmentHomeBinding
import com.sofia.izipizisocialnetwork.utils.FOLLOW
import com.sofia.izipizisocialnetwork.utils.POST

class HomeFragment : Fragment() {
private lateinit var binding: FragmentHomeBinding
private var postList=ArrayList<Post>()
    private lateinit var adapter: PostAdapter
    private var followList=ArrayList<User>()
    private lateinit var followAdapter: FollowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater, container, false)
        adapter= PostAdapter(requireContext(), postList)
        binding.postRv.layoutManager=LinearLayoutManager(requireContext())
        binding.postRv.adapter=adapter

        followAdapter=FollowAdapter(requireContext(), followList)
        binding.followRv.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.followRv.adapter=followAdapter

        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbar3)

        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW).get()
            .addOnSuccessListener {
            var tempList = ArrayList<User>()
                followList.clear()
            for(i in it.documents){
                var user: User=i.toObject<User>()!!
                tempList.add(user)
            }
                followList.addAll(tempList)
                followAdapter.notifyDataSetChanged()
        }

        Firebase.firestore.collection(POST).get().addOnSuccessListener {
            var tempList=ArrayList<Post>()
            postList.clear()
            for(i in it.documents){
                var post:Post=i.toObject<Post>()!!
                tempList.add(post)

            }
            postList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    companion object {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}