package com.example.planty

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.planty.fragments.HomeFragment
import android.app.FragmentTransaction
import com.example.planty.fragments.AddPlantFragment
import com.example.planty.fragments.CollectionFragment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // charger notre repository
        val repo = PlantRepository()

        // mettre a jour la liste de plantes
        repo.updateData {
            // injecter le fragment dans notre boite (fragment_container)
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, AddPlantFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}



