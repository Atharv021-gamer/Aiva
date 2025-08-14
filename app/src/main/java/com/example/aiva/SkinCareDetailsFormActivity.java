package com.example.aiva;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ImageView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class SkinCareDetailsFormActivity extends AppCompatActivity {

    private EditText etAge, etBudget, etConcerns;
    private Spinner spinnerGender, spinnerSkinType;
    private Button btnProducts, btnRemedies;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_care_details_form);

        etAge = findViewById(R.id.etAge);
        etBudget = findViewById(R.id.etBudget);
        etConcerns = findViewById(R.id.etConcerns);
        spinnerGender = findViewById(R.id.spinnerGender);
        spinnerSkinType = findViewById(R.id.spinnerSkinType);
        btnProducts = findViewById(R.id.btnProducts);
        btnRemedies = findViewById(R.id.btnRemedies);
        ivBack = findViewById(R.id.ivBack);

        // Gender Spinner Data
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Male", "Female", "Other"});
        spinnerGender.setAdapter(genderAdapter);

        // Skin Type Spinner Data
        ArrayAdapter<String> skinTypeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Oily", "Dry", "Combination", "Sensitive"});
        spinnerSkinType.setAdapter(skinTypeAdapter);

        btnProducts.setOnClickListener(v -> openRecommendation("products"));
        btnRemedies.setOnClickListener(v -> openRecommendation("remedies"));

        ivBack.setOnClickListener(v -> onBackPressed());
    }

    private void openRecommendation(String type) {
        Intent intent;
        if (type.equals("products")) {
            intent = new Intent(this, SkinCareProductsActivity.class);
        } else {
            intent = new Intent(this, SkinCareRemediesActivity.class);
        }

        intent.putExtra("age", etAge.getText().toString());
        intent.putExtra("gender", spinnerGender.getSelectedItem().toString());
        intent.putExtra("skinType", spinnerSkinType.getSelectedItem().toString());
        intent.putExtra("budget", etBudget.getText().toString());
        intent.putExtra("concerns", etConcerns.getText().toString());
        startActivity(intent);
    }
}
