package com.maksapp.pinskdrev

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.maksapp.pinskdrev.EventBus.CategoryClick
import com.maksapp.pinskdrev.databinding.ActivityMainBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {
    private lateinit var userRef: DatabaseReference
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_catalog,
                R.id.navigation_orders,
                R.id.navigation_account
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.layout_action_bar)

    }

    fun init() {
        userRef = FirebaseDatabase.getInstance().reference
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //Event Bus
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        EventBus.getDefault().unregister(this)
        super.onPause()
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onCategorySelected(event: CategoryClick) {
        if (event.isSuccess) {
            //Toast.makeText(context, "Click to " +event.category.name, Toast.LENGTH_SHORT ).show()
            findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_catalog_to_navigation_product)
        }
    }
}