package com.lconder.covid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private TextView btnSignUp, btnReset;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, FavoriteFragment.class));
            finish();
        }

        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        btnLogin = (Button) findViewById(R.id.btn_login);

        auth = FirebaseAuth.getInstance();

    }

    public void loadSignUpView(View v) {
        btnSignUp = (TextView) findViewById(R.id.link_signup);
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    public void loadResetView(View v) {
        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
    }

    public void login(View view) {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        Validator validator = new Validator();

        if (TextUtils.isEmpty(email) || !validator.validateEmail(email)) {
            Toast.makeText(getApplicationContext(), getString(R.string.enter_valid_email), Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), getString(R.string.enter_valid_password), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!validator.validatePassword(password)) {
            Toast.makeText(getApplicationContext(), getString(R.string.password_length), Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            Log.i("ERROR_LOGIN", String.valueOf(task.getException()));
                            Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                        } else {
                            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                            SharedPreferences SP = getSharedPreferences("com.lconder.covid_preferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = SP.edit();
                            editor.putString("uid", currentFirebaseUser.getUid());
                            editor.apply();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}
