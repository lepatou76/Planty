package com.example.planty.fragments

import android.annotation.SuppressLint
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planty.MainActivity
import com.example.planty.PlantRepository.Singleton.plantList
import com.example.planty.R
import com.example.planty.adapter.PlantAdapter
import com.example.planty.adapter.PlantItemDecoration

@SuppressLint("ValidFragment")
class CollectionFragment(
    private val context: MainActivity
) : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_collection, container, false)

        // recuperer ma recyclerview
        val collectionRecyclerView = view?.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecyclerView?.adapter = PlantAdapter(context, plantList.filter {it.liked}, R.layout.item_vertical_plant)
        collectionRecyclerView?.layoutManager = LinearLayoutManager(context)
        collectionRecyclerView?.addItemDecoration(PlantItemDecoration())

        return view
    }

}