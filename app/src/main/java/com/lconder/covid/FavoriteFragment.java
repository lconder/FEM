package com.lconder.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lconder.covid.models.Country;
import com.lconder.covid.models.CountryViewModel;
import com.lconder.covid.views.FavoriteListAdapter;
import com.lconder.covid.views.RecyclerViewClickListener;

import java.util.List;

public class FavoriteFragment extends Fragment implements RecyclerViewClickListener {

    private CountryViewModel countryViewModel;
    FavoriteListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View mView = inflater.inflate(R.layout.activity_favorite, null);

        RecyclerView recyclerView = mView.findViewById(R.id.recyclerview);
        adapter = new FavoriteListAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        countryViewModel.getFavorites().observe(getActivity(), new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                adapter.setFavorites(countries);
            }
        });

        FloatingActionButton fab = mView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, CountriesActivity.class);
                context.startActivity(intent);
            }
        });

        downLoadFavorites();

        return mView;
    }


    @Override
    public void recyclerViewListClicked(int position) {
        final Country country = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), CountryActivity.class);
        intent.putExtra("CODE", country.getCode());
        intent.putExtra("NAME", country.getName());
        this.startActivity(intent);
    }

    public void downLoadFavorites() {
        SharedPreferences SP = getActivity().getSharedPreferences(getString(R.string.project), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = SP.edit();
        boolean favorites_downloaded = SP.getBoolean(getString(R.string.favs_downloaded), false);
        String favorites = SP.getString(getString(R.string.favorites), null);

        if(!favorites_downloaded && favorites != null) {
            String[] _favorites = favorites.split(",");
            for(String code: _favorites){
                countryViewModel.favorite(code, true);
            }
            editor.putBoolean(getString(R.string.favs_downloaded), true);
            editor.apply();
            adapter.notifyDataSetChanged();
        }
    }
}