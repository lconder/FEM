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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        code = getIntent().getStringExtra("CODE");
        name = getIntent().getStringExtra("NAME");
        Log.i("COUNTRY", "onCreate: " + code);
        Log.i("COUNTRY", "onCreate: " + name);

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
                Log.i("Country", String.valueOf(response.body().getCases()));
                Picasso.get()
                        .load("https://www.countryflags.io/"+code+"/flat/64.png")
                        .into(ivFlag);
                tvCountry.setText(response.body().getCountry());
                tvActive.setText(response.body().getActive().toString());
                tvRecovered.setText(response.body().getRecovered().toString());
                tvDeaths.setText(response.body().getDeaths().toString());
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