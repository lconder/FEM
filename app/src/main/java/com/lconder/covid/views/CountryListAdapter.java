package com.lconder.covid.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lconder.covid.R;
import com.lconder.covid.models.Country;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    static class CountryViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvCountryName;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountryName = itemView.findViewById(R.id.country_name);
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
        View itemView = mInflater.inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        if(mCountries!=null) {
            Country current = mCountries.get(position);
            holder.tvCountryName.setText(current.getEs_name());
        }
    }

    public void setCountries(List<Country> countries) {
        Log.i("COUNTRIES", String.valueOf(mCountries));
        mCountries = countries;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mCountries!=null) {
            return mCountries.size();
        }
        return 0;
    }


}
