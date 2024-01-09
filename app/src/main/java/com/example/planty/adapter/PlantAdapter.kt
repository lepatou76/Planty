package com.example.planty.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.planty.MainActivity
import com.example.planty.PlantModel
import com.example.planty.PlantPopup
import com.example.planty.PlantRepository
import com.example.planty.R

class PlantAdapter(
    val context: MainActivity,
    private val plantList: List<PlantModel>,
    private val layoutId: Int) : RecyclerView.Adapter<PlantAdapter.ViewHolder>(){

    // boite pour ranger tous les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val plantImage = view.findViewById<ImageView>(R.id.image_item)
        val plantName: TextView? = view.findViewById(R.id.name_item)
        val plantDescription: TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = plantList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recupérer les informations de la plante
        val currentPlant = plantList[position]
        // recupérer le repository
        val repo = PlantRepository()
        // utiliser glide pour recuperer image a partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.plantImage)
        // mettre a jour le nom de la plante
        holder.plantName?.text  = currentPlant.name
        // mettre a jour la description
        holder.plantDescription?.text = currentPlant.description
        // verifier si la plante a été likée
        if(currentPlant.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        }
        else {
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }
        // rajouter une interaction sur cette étoile
        holder.starIcon.setOnClickListener{
            // inverser si le bouton est like ou non
            currentPlant.liked = !currentPlant.liked
            // mettre a jour l'objet plante
            repo.updatePlant(currentPlant)
        }

        // interaction lors du clic sur une plante
        holder.itemView.setOnClickListener {
            // afficher la popup
            PlantPopup(this, currentPlant).show()
        }



    }
}