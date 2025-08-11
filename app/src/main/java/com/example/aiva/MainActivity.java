package com.example.aiva;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    MaterialToolbar toolbar;

    Button btnMealPlanner, btnShoppingAI, btnSkinCare, btnClothingAI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        toolbar = findViewById(R.id.topAppBar);
        btnMealPlanner = findViewById(R.id.btn_mealPlanner);
        btnShoppingAI = findViewById(R.id.btn_shoppingAI);
        btnSkinCare = findViewById(R.id.btn_skinCare);
        btnClothingAI = findViewById(R.id.btn_clothingAI);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Drawer item click
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_settings) {
                // Open settings
            } else if (id == R.id.nav_about) {
                // About app
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Bottom nav item click
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                // Home
            } else if (id == R.id.nav_favorites) {
                // Favorites
            } else if (id == R.id.nav_history) {
                // History
            } else if (id == R.id.nav_profile) {
                // Profile
            }
            return true;
        });

        // Quick action buttons
        btnMealPlanner.setOnClickListener(v -> {
            // Open meal planner activity
        });

        btnShoppingAI.setOnClickListener(v -> {
            // Open shopping AI
        });

        btnSkinCare.setOnClickListener(v -> {
            // Open skin care AI
        });

        btnClothingAI.setOnClickListener(v -> {
            // Open clothing AI
        });
    }
}
