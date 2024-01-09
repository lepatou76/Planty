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
import com.example.planty.R
import com.example.planty.adapter.PlantAdapter
import com.example.planty.adapter.PlantItemDecoration


@SuppressLint("ValidFragment")
class HomeFragment(
    private val context: MainActivity
) : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater?.inflate(R.layout.fragment_home, container, false)

            // creer une liste qui va stocker ces plantes
            val plantList = arrayListOf<PlantModel>()

            //enregistrer une premiere plante dans notre liste (pissenlit)
            plantList.add(
                PlantModel(
                "Pissenlit",
                "Jaune Soleit",
                "https://cdn.pixabay.com/photo/2023/05/22/14/49/dandelion-8010882_1280.jpg",
                false)
            )
            //enregistrer une seconde plante dans notre liste (rose)
            plantList.add(
                PlantModel(
                "Rose",
                "Qui pique",
                "https://cdn.pixabay.com/photo/2014/04/10/11/24/rose-320868_1280.jpg",
                false)
            )
            //enregistrer une troisieme plante dans notre liste (cactus)
            plantList.add(
                PlantModel(
                "Cactus",
                "A des aiguilles",
                "https://cdn.pixabay.com/photo/2014/07/29/08/55/cactus-404362_1280.jpg",
                true)
            )
            //enregistrer une quatrieme plante dans notre liste (tulipe)
            plantList.add(
                PlantModel(
                "Tulipe",
                "Sent super bon",
                "https://cdn.pixabay.com/photo/2017/03/30/18/38/tulip-2189317_1280.jpg",
                false)
            )


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
