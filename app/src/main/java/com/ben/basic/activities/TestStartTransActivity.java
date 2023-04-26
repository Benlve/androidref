package com.ben.basic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ben.basic.R;

public class TestStartTransActivity extends AppCompatActivity {

    private static final String ACTION = "com.rivotek.car.settings.ui.OpenSoundEffectActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_start_trans);

        Button startOtherTransActivity = findViewById(R.id.start_other_trans_activity);
        startOtherTransActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(ACTION);
                startActivity(intent);
            }
        });

    }
}