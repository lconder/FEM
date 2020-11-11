package com.lconder.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText inputEmail;
    private TextView btnLogin;
    private ProgressBar progressBar;
    private Button btnReset;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        inputEmail = findViewById(R.id.input_email);
        progressBar = findViewById(R.id.progress_bar);

        auth = FirebaseAuth.getInstance();
    }

    public void reset(View v) {
        String email = inputEmail.getText().toString();
        Validator validator = new Validator();

        if (TextUtils.isEmpty(email) || !validator.validateEmail(email)) {
            Toast.makeText(getApplicationContext(), getString(R.string.enter_valid_email), Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Log.i("ERROR", String.valueOf(task.getException()));
                            Toast.makeText(ResetPasswordActivity.this, getString(R.string.general_error), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetPasswordActivity.this, getString(R.string.reset_password_successfully), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        progressBar.setVisibility(View.GONE);
    }

    public void loadLogInView(View v) {
        startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
    }


}