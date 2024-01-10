package com.example.planty

import android.app.Fragment
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.planty.fragments.HomeFragment
import android.app.FragmentTransaction
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import com.example.planty.fragments.AddPlantFragment
import com.example.planty.fragments.CollectionFragment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // importer les boutons de navigation
        val homeButton = findViewById<ImageView>(R.id.button_home_page)
        val collectionButton = findViewById<ImageView>(R.id.button_collection_page)
        val addPlantButton = findViewById<ImageView>(R.id.button_add_plant_page)

        homeButton.setOnClickListener {
            loadFragment(HomeFragment(this))
            findViewById<TextView>(R.id.page_title).text = getString(R.string.home_page_title)
            homeButton.setColorFilter(ContextCompat.getColor(this, R.color.black))
            collectionButton.setColorFilter(ContextCompat.getColor(this, R.color.lightGray))
            addPlantButton.setColorFilter(ContextCompat.getColor(this, R.color.lightGray))
        }
        collectionButton.setOnClickListener {
            loadFragment(CollectionFragment(this))
            findViewById<TextView>(R.id.page_title).text = getString(R.string.collection_page_title)
            collectionButton.setColorFilter(ContextCompat.getColor(this, R.color.black))
            homeButton.setColorFilter(ContextCompat.getColor(this, R.color.lightGray))
            addPlantButton.setColorFilter(ContextCompat.getColor(this, R.color.lightGray))
        }
        addPlantButton.setOnClickListener {
            loadFragment(AddPlantFragment(this))
            findViewById<TextView>(R.id.page_title).text = getString(R.string.add_plant_page_title)
            addPlantButton.setColorFilter(ContextCompat.getColor(this, R.color.black))
            homeButton.setColorFilter(ContextCompat.getColor(this, R.color.lightGray))
            collectionButton.setColorFilter(ContextCompat.getColor(this, R.color.lightGray))
        }

        loadFragment(HomeFragment(this))
    }

    private fun loadFragment(fragment: Fragment) {
        // charger notre repository
        val repo = PlantRepository()

        // mettre a jour la liste de plantes
        repo.updateData {
            // injecter le fragment dans notre boite (fragment_container)
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}




