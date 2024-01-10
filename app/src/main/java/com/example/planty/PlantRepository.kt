package com.example.planty

import android.net.Uri
import com.example.planty.PlantRepository.Singleton.databaseRef
import com.example.planty.PlantRepository.Singleton.downloadUri
import com.example.planty.PlantRepository.Singleton.plantList
import com.example.planty.PlantRepository.Singleton.storageReference
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.UUID

class PlantRepository {

    object Singleton {
        // donner le lien pour acceder au bucket
        private val BUCKET_URL: String = "gs://planty-1a544.appspot.com"

        // se connecter a notre espace de stockage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)

        // se connecter a la reference "plants"
        val databaseRef = FirebaseDatabase.getInstance().getReference("plants")

        // creer une liste qui va contenir les plantes
        val plantList = arrayListOf<PlantModel>()

        // contenir le lien de l'image courante
        var downloadUri: Uri? = null
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

    // Creer une fonction pour envoyer des fichiers sur le storege
    fun uploadImage(file: Uri, callback: () -> Unit) {
        // verifier que ce fichier n'est pas null
        if(file != null) {
            val fileName = UUID.randomUUID().toString() + ".jpg"
            val ref = storageReference.child(fileName)
            val uploadTask = ref.putFile(file)

            // demarrer la tache d'envoi
            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>>  { task ->

                // s'il y a eu un probleme lors de l'envoi du fichier
                if(!task.isSuccessful) {
                    task.exception?.let {throw it}
                }
                return@Continuation ref.downloadUrl

            }).addOnCompleteListener { task ->
                // verifier si tout a bien fonctionné
                if(task.isSuccessful) {
                    // récuperer l'image
                    downloadUri = task.result
                    callback()
                }
            }
        }
    }

    // mettre a jour un objet plante en bdd
    fun updatePlant(plant: PlantModel) {
        databaseRef.child(plant.id).setValue(plant)
    }
    // inserer une nouvelle plante en bdd
    fun insertPlant(plant: PlantModel) {
        databaseRef.child(plant.id).setValue(plant)
    }
    // supprimer une plante de la base
    fun deletePlant(plant: PlantModel) {
        databaseRef.child(plant.id).removeValue()
    }

}