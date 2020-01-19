package com.alfinapp.ui.welcome;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alfinapp.R;
import com.alfinapp.ui.home.HomeActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        findViewById(R.id.btn_next).setOnClickListener(view -> init());

    }

    private void init() {
        finish();
        startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }
}
