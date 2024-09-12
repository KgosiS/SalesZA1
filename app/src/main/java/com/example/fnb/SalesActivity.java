package com.example.fnb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.gridlayout.widget.GridLayout;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

public class SalesActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;
    private SearchView searchView;
    private GridLayout categoryGrid;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        searchView = findViewById(R.id.search_view);
        categoryGrid = findViewById(R.id.category_grid);

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // Search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform search based on query
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Update map and categories based on the search
                return false;
            }
        });

        // Category button click listeners
        findViewById(R.id.btn_groceries).setOnClickListener(view -> showStoresOnMap("groceries"));
        findViewById(R.id.btn_clothes).setOnClickListener(view -> showStoresOnMap("clothes"));
        findViewById(R.id.btn_furniture).setOnClickListener(view -> showStoresOnMap("furniture"));
        findViewById(R.id.btn_fast_food).setOnClickListener(view -> showStoresOnMap("fast food"));
        findViewById(R.id.btn_home_appliances).setOnClickListener(view -> showStoresOnMap("home appliances"));
        findViewById(R.id.btn_car_parts).setOnClickListener(view -> showStoresOnMap("car parts"));
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        // Add markers for initial category (groceries)
        showStoresOnMap("groceries");

        // Set a click listener on the markers
        googleMap.setOnMarkerClickListener(marker -> {
            // Get the website URL from the marker's snippet
            String websiteUrl = marker.getSnippet();
            if (websiteUrl != null && !websiteUrl.isEmpty()) {
                // Open the website in a browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
                startActivity(intent);
            }
            return true;
        });
        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout1);
        navigationView = findViewById(R.id.navigation_view);

        // Set up the Drawer Toggle
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Handle navigation item clicks
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_sales){
                    startActivity(new Intent(SalesActivity.this, SalesActivity.class));
                }else if (item.getItemId() == R.id.nav_tips){
                    startActivity(new Intent(SalesActivity.this, TipsActivity.class));
                }else if(item.getItemId() == R.id.nav_voucher){
                    startActivity(new Intent(SalesActivity.this, VouchersActivity.class));
                }else if(item.getItemId() == R.id.nav_watch_ads){
                    startActivity(new Intent(SalesActivity.this, WatchAdsActivity.class));
                }else if(item.getItemId() == R.id.nav_finance){
                    startActivity(new Intent(SalesActivity.this, FinancialLiteracyActivity.class));
                }

                drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer after clicking
                return true;
            }
        });
    }

    // Method to show stores based on the category selected
    private void showStoresOnMap(String category) {
        // Clear the map for new category markers
        googleMap.clear();

        // Add markers for different categories
        if (category.equals("groceries")) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-26.2041, 28.0473))  // Johannesburg
                    .title("Pick n Pay")
                    .snippet("https://www.picknpay.co.za"));

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-33.9249, 18.4241))  // Cape Town
                    .title("Checkers")
                    .snippet("https://www.checkers.co.za"));

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-25.7461, 28.1881))  // Pretoria
                    .title("Spar")
                    .snippet("https://www.spar.co.za"));
            // Add more grocery stores...
        } else if (category.equals("clothes")) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-33.9249, 18.4241))  // Cape Town
                    .title("Mr Price")
                    .snippet("https://www.mrprice.co.za"));

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-26.2041, 28.0473))  // Johannesburg
                    .title("Woolworths")
                    .snippet("https://www.woolworths.co.za"));

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-29.8587, 31.0218))  // Durban
                    .title("Edgars")
                    .snippet("https://www.edgars.co.za"));
            // Add more clothing stores...
        } else if (category.equals("furniture")) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-26.2041, 28.0473))  // Johannesburg
                    .title("IKEA")
                    .snippet("https://www.ikea.com"));

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-33.9249, 18.4241))  // Cape Town
                    .title("OK Furniture")
                    .snippet("https://www.okfurniture.co.za"));

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-25.7461, 28.1881))  // Pretoria
                    .title("Coricraft")
                    .snippet("https://www.coricraft.co.za"));
            // Add more furniture stores...
        } else if (category.equals("fast food")) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-26.2041, 28.0473))  // Johannesburg
                    .title("McDonald's")
                    .snippet("https://www.mcdonalds.co.za"));

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-33.9249, 18.4241))  // Cape Town
                    .title("KFC")
                    .snippet("https://www.kfc.co.za"));

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-25.7461, 28.1881))  // Pretoria
                    .title("Nando's")
                    .snippet("https://www.nandos.co.za"));
            // Add more fast food stores...
        } else if (category.equals("home appliances")) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-26.2041, 28.0473))  // Johannesburg
                    .title("Game")
                    .snippet("https://www.game.co.za"));

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-33.9249, 18.4241))  // Cape Town
                    .title("Makro")
                    .snippet("https://www.makro.co.za"));

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-25.7461, 28.1881))  // Pretoria
                    .title("HiFi Corp")
                    .snippet("https://www.hificorp.co.za"));
            // Add more home appliance stores...
        } else if (category.equals("car parts")) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-26.2041, 28.0473))  // Johannesburg
                    .title("AutoZone")
                    .snippet("https://www.autozone.co.za"));

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-33.9249, 18.4241))  // Cape Town
                    .title("Midas")
                    .snippet("https://www.midas.co.za"));

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(-25.7461, 28.1881))  // Pretoria
                    .title("Bosch Car Service")
                    .snippet("https://www.boschcarservice.co.za"));
            // Add more car parts stores...
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
