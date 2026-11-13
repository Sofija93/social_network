package com.sofia.izipizisocialnetwork.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.sofia.izipizisocialnetwork.Models.Post
import com.sofia.izipizisocialnetwork.Models.Reel
import com.sofia.izipizisocialnetwork.R
import com.sofia.izipizisocialnetwork.adapters.MyPostRvAdapter
import com.sofia.izipizisocialnetwork.adapters.MyReelAdapter
import com.sofia.izipizisocialnetwork.databinding.FragmentMyReealsBinding
import com.sofia.izipizisocialnetwork.databinding.FragmentReelBinding
import com.sofia.izipizisocialnetwork.utils.REEL

class MyReelsFragment : Fragment() {
    private lateinit var binding: FragmentMyReealsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyReealsBinding.inflate(inflater, container, false)
        var reelList=ArrayList<Reel>()
        var adapter= MyReelAdapter(requireContext(), reelList)
        binding.rv.layoutManager= StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter=adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).get().addOnSuccessListener {

            var tempList= arrayListOf<Reel>()
            for (i in it.documents){
                var reel: Reel =i.toObject<Reel>()!!
                tempList.add(reel)
            }
            reelList.addAll(tempList)
            adapter.notifyDataSetChanged()

        }
        return binding.root
    }

    companion object {

    }
}