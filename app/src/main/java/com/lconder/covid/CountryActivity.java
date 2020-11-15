package com.lconder.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lconder.covid.models.DataService;
import com.lconder.covid.models.RetrofitClientInstance;
import com.lconder.covid.models.pojo.RetroCountry;
import com.lconder.covid.models.pojo.RetroLamp;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    DataService service;
    RelativeLayout lighter;
    SharedPreferences SP;
    String url = "127.0.0.1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        SP = getSharedPreferences(getString(R.string.project), Context.MODE_PRIVATE);

        code = getIntent().getStringExtra("CODE");
        name = getIntent().getStringExtra("NAME");

        formatter = new DecimalFormat("#,###,###");

        ivFlag = findViewById(R.id.flag);
        tvCountry = findViewById(R.id.country);
        tvActive = findViewById(R.id.active);
        tvRecovered = findViewById(R.id.recovered);
        tvDeaths = findViewById(R.id.deaths);

        lighter = findViewById(R.id.lighter);

        progressDialog = new ProgressDialog(CountryActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        service = RetrofitClientInstance.getInstance().create(DataService.class);
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
                if(response.body().getCasesPerOneMillion() != null) {
                    sendRequestToLamp(
                            getColorObject(response.body().getCasesPerOneMillion()),
                            getColor(response.body().getCasesPerOneMillion())
                    );
                }
            }
            @Override
            public void onFailure(Call<RetroCountry> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(
                        CountryActivity.this,
                        R.string.general_error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        url = SP.getString(getString(R.string.ip_key), getString(R.string.defaultIP));
    }

    private void sendRequestToLamp(RetroLamp.BodyRequest colorObject, final int color) {

        service = RetrofitClientInstance.getInstance().create(DataService.class);
        url = SP.getString(getString(R.string.ip_key), getString(R.string.defaultIP));
        Call<RetroLamp> call = service.lamp(
                url + "/ring/color/", colorObject
        );
        call.enqueue(new Callback<RetroLamp>() {
            @Override
            public void onResponse(Call<RetroLamp> call, Response<RetroLamp> response) {
                lighter.setBackgroundColor(getResources().getColor(color));
            }
            @Override
            public void onFailure(Call<RetroLamp> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.general_error + " al encender PRISMA", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private RetroLamp.BodyRequest getColorObject(Integer casesPerMillion) {
        int rate = casesPerMillion  * 100;
        double ratePerMillion = rate / 1000000.00;
        if(ratePerMillion < 0.5) {
            return new RetroLamp.BodyRequest(0, 255, 0);
        } else if(ratePerMillion > 0.5 && ratePerMillion < 1.5) {
            return new RetroLamp.BodyRequest(255, 255, 0);
        } else if(ratePerMillion > 1.5) {
            return new RetroLamp.BodyRequest(255, 0, 0);
        }
        return new RetroLamp.BodyRequest(0, 255, 0);
    }

    private int getColor(Integer casesPerMillion) {
        int rate = casesPerMillion  * 100;
        double ratePerMillion = rate / 1000000.00;
        if(ratePerMillion < 0.5) {
            return R.color.green;
        } else if(ratePerMillion > 0.5 && ratePerMillion < 1.5) {
            return R.color.yellow;
        } else if(ratePerMillion > 1.5) {
            return R.color.red;
        }
        return R.color.green;
    }
}