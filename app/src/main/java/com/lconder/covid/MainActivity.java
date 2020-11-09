package com.lconder.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lconder.covid.models.AppDatabase;
import com.lconder.covid.models.Country;
import com.lconder.covid.models.CountryViewModel;
import com.lconder.covid.views.FavoriteListAdapter;
import com.lconder.covid.views.RecyclerViewClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    AppDatabase db;
    private CountryViewModel countryViewModel;
    FavoriteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user==null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new FavoriteListAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        countryViewModel.getFavorites().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                adapter.setFavorites(countries);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, CountriesActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    @Override
    public void recyclerViewListClicked(int position) {
        final Country country = adapter.getItem(position);
        Toast.makeText(getApplicationContext(), "Presionado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CountryActivity.class);
        intent.putExtra("CODE", country.getCode());
        intent.putExtra("NAME", country.getName());
        this.startActivity(intent);
    }
}