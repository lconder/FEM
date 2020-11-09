package com.lconder.covid.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lconder.covid.R;
import com.lconder.covid.models.Country;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> implements Filterable {

    static class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvCountryName;
        RecyclerViewClickListener recyclerViewClickListener;

        public CountryViewHolder(@NonNull View itemView, RecyclerViewClickListener recyclerViewClickListener) {
            super(itemView);
            tvCountryName = itemView.findViewById(R.id.country_name);
            this.recyclerViewClickListener = recyclerViewClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListener.recyclerViewListClicked(getAdapterPosition());
        }
    }

    private final LayoutInflater mInflater;
    private List<Country> mCountries;
    private List<Country> mCountriesAll;
    private RecyclerViewClickListener mRecyclerViewClickListener;

    public CountryListAdapter(Context context, RecyclerViewClickListener recyclerViewClickListener) {
        mInflater = LayoutInflater.from(context);
        mRecyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(itemView, mRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        if(mCountries!=null) {
            Country current = mCountries.get(position);
            holder.tvCountryName.setText(current.getEs_name());
        }
    }

    public void setCountries(List<Country> countries) {
        mCountries = countries;
        mCountriesAll = new ArrayList<>(countries);
        notifyDataSetChanged();
    }


    public Country getItem(int position) {
        return mCountries.get(position);
    }

    @Override
    public int getItemCount() {
        if(mCountries!=null) {
            return mCountries.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Country> filteredList = new ArrayList<>();

            if(charSequence.toString().isEmpty()) {
                filteredList.addAll(mCountriesAll);
            } else {
                for (Country country: mCountriesAll) {
                    if(country.getEs_name().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(country);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mCountries.clear();
            mCountries.addAll((Collection<? extends Country>) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
