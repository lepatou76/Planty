package com.example.planty.fragments

import android.annotation.SuppressLint
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.planty.MainActivity
import com.example.planty.PlantModel
import com.example.planty.PlantRepository.Singleton.plantList
import com.example.planty.R
import com.example.planty.adapter.PlantAdapter
import com.example.planty.adapter.PlantItemDecoration


@SuppressLint("ValidFragment")
class HomeFragment(
    private val context: MainActivity
) : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater?.inflate(R.layout.fragment_home, container, false)


            // recuperer le premier recycleview
            val horizontalRecyclerView = view?.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
            horizontalRecyclerView?.adapter = PlantAdapter(context, plantList, R.layout.item_horizontal_plant)

            // recuperer le second recycleview
            val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
            verticalRecyclerView?.adapter = PlantAdapter(context, plantList, R.layout.item_vertical_plant)
            verticalRecyclerView?.addItemDecoration(PlantItemDecoration())

            return view
        }
    }
