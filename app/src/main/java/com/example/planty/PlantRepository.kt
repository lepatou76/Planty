package com.example.planty

import com.example.planty.PlantRepository.Singleton.databaseRef
import com.example.planty.PlantRepository.Singleton.plantList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PlantRepository {

    object Singleton {
        // se connecter a la reference "plants"
        val databaseRef = FirebaseDatabase.getInstance().getReference("plants")

        // creer une liste qui va contenir les plantes
        val plantList = arrayListOf<PlantModel>()
    }

    fun updateData(callback: () -> Unit) {
        // absorber les données depuis la databaseRef -> liste de plantes
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciennes plantes
                plantList.clear()
                // recolter la liste
                for (ds in snapshot.children) {
                    // construire un objet plante
                    val plant = ds.getValue(PlantModel::class.java)
                    // verifier que la plante n'est pas nulle
                    if(plant != null) {
                        // ajouter la plante à notre liste
                        plantList.add(plant)
                    }
                }
                // actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    // mettre a jour un objet plante en bdd
    fun updatePlant(plant: PlantModel) {
        databaseRef.child(plant.id).setValue(plant)
    }
    // supprimer une plante de la base
    fun deletePlant(plant: PlantModel) {
        databaseRef.child(plant.id).removeValue()
    }

}