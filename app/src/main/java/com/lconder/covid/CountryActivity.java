package com.lconder.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lconder.covid.models.DataService;
import com.lconder.covid.models.RetrofitClientInstance;
import com.lconder.covid.models.pojo.RetroCountry;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    ImageView ivFlag;
    TextView tvCountry;
    TextView tvActive;
    TextView tvRecovered;
    TextView tvDeaths;
    String code;
    String name;
    DecimalFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        code = getIntent().getStringExtra("CODE");
        name = getIntent().getStringExtra("NAME");

        formatter = new DecimalFormat("#,###,###");

        ivFlag = findViewById(R.id.flag);
        tvCountry = findViewById(R.id.country);
        tvActive = findViewById(R.id.active);
        tvRecovered = findViewById(R.id.recovered);
        tvDeaths = findViewById(R.id.deaths);

        progressDialog = new ProgressDialog(CountryActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        DataService service = RetrofitClientInstance.getInstance().create(DataService.class);
        Call<RetroCountry> call = service.getById(name);
        call.enqueue(new Callback<RetroCountry>() {
            @Override
            public void onResponse(Call<RetroCountry> call, Response<RetroCountry> response) {
                progressDialog.dismiss();
                Picasso.get()
                        .load("https://www.countryflags.io/"+code+"/flat/64.png")
                        .error(R.drawable.flag)
                        .into(ivFlag)
                ;
                assert response.body() != null;
                if(response.body().getCountry() != null) {
                    tvCountry.setText(response.body().getCountry().toString());
                } else {
                    tvCountry.setText(R.string.not_info_available);
                }

                if(response.body().getActive() != null) {
                    tvActive.setText(formatter.format(response.body().getActive()));
                } else {
                    tvActive.setText(R.string.not_info_available);
                }

                if(response.body().getRecovered() != null) {
                    tvRecovered.setText(formatter.format(response.body().getRecovered()));
                } else {
                    tvRecovered.setText(R.string.not_info_available);
                }

                if(response.body().getDeaths() != null) {
                    tvDeaths.setText(formatter.format(response.body().getDeaths()));
                } else {
                    tvDeaths.setText(R.string.not_info_available);
                }
            }
            @Override
            public void onFailure(Call<RetroCountry> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(
                        CountryActivity.this,
                        "Ha ocurrido un error",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}