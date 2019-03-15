package com.caraxian.sifam;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;

public class ErrorDetected extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_detected);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("SIFAM: Error Detected");
        TextView t = (TextView) findViewById(R.id.errorMessage);
        t.setText("Looks like something broke.\nSorry about that\n\nHeres the error message:\n\n" + SIFAM.errorMessage);
    }
}
