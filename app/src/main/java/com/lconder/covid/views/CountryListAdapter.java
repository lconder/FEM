package com.lconder.covid.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lconder.covid.R;
import com.lconder.covid.models.Country;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    class CountryViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvCountryName;
        private final ImageView ivCountryImage;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountryName = itemView.findViewById(R.id.country_name);
            ivCountryImage = itemView.findViewById(R.id.country_image);
        }
    }

    private final LayoutInflater mInflater;
    private List<Country> mCountries;

    public CountryListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_country_image, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        if(mCountries!=null) {
            Country current = mCountries.get(position);
            holder.tvCountryName.setText(current.getEs_name());
            Picasso.get().load(current.getImage()).into(holder.ivCountryImage);
        }
    }

    public void setCountries(List<Country> countries) {
        mCountries = countries;
    }

    @Override
    public int getItemCount() {
        if(mCountries!=null) {
            Log.i("COUNT", String.valueOf(mCountries.size()));
            return mCountries.size();
        }
        Log.i("COUNT", "0 because is null");
        return 0;
    }




}
