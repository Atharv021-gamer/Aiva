package com.example.aiva;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SkinCareRemediesActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvRemediesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_care_remedies);

        ivBack = findViewById(R.id.ivBack);
        tvRemediesList = findViewById(R.id.tvRemediesList);

        ivBack.setOnClickListener(v -> onBackPressed());

        // Example remedies
        String remedies = "🥒 Cucumber slices for puffy eyes\n" +
                "🍯 Honey mask for acne\n" +
                "🥭 Aloe vera gel for hydration\n" +
                "🌿 Turmeric & milk paste for glow\n" +
                "🍋 Lemon juice (diluted) for dark spots";

        tvRemediesList.setText(remedies);
    }
}
