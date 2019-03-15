package com.caraxian.sifam;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AppPreferences extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new AppPrefFragment()).commit();
    }
}
