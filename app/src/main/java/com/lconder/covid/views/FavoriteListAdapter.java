package com.lconder.covid.views;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lconder.covid.CountryActivity;
import com.lconder.covid.R;
import com.lconder.covid.models.Country;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder> {

    class FavoriteViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvCountryName;
        private final ImageView ivCountryImage;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountryName = itemView.findViewById(R.id.country_name);
            ivCountryImage = itemView.findViewById(R.id.country_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, CountryActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<Country> mCountries;

    public FavoriteListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_country_image, parent, false);
        return new FavoriteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        if(mCountries!=null) {
            Country current = mCountries.get(position);
            holder.tvCountryName.setText(current.getEs_name());
            Picasso.get().load(current.getImage()).into(holder.ivCountryImage);
        }
    }

    public void setFavorites(List<Country> countries) {
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
