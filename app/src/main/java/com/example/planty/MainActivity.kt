package com.example.planty

import android.app.FragmentManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.planty.fragments.HomeFragment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // injecter le fragment dans notre boite (fragment_container)
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, HomeFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }


}



