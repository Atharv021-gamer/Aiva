package com.example.aiva;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SkinCareChoiceActivity extends AppCompatActivity {

    private ImageView ivBack;
    private CardView cardProducts, cardRemedies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_care_choice);

        ivBack = findViewById(R.id.ivBack);
        cardProducts = findViewById(R.id.cardProducts);
        cardRemedies = findViewById(R.id.cardRemedies);

        // Back button action
        ivBack.setOnClickListener(v -> finish());

        // Go to Products Activity
        cardProducts.setOnClickListener(v -> {
            Intent intent = new Intent(SkinCareChoiceActivity.this, SkinCareProductsActivity.class);
            startActivity(intent);
        });

        // Go to Remedies Activity
        cardRemedies.setOnClickListener(v -> {
            Intent intent = new Intent(SkinCareChoiceActivity.this, SkinCareRemediesActivity.class);
            startActivity(intent);
        });
    }
}
