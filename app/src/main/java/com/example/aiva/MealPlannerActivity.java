package com.example.aiva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

public class MealPlannerActivity extends AppCompatActivity {

    private TextInputEditText inputAge, inputHeight, inputWeight, inputBudget;
    private RadioGroup radioGroupGender;
    private Spinner spinnerMealPreference;
    private Button btnGetSuggestions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_planner);

        // Toolbar setup with back arrow
        MaterialToolbar toolbar = findViewById(R.id.mealPlannerToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(MealPlannerActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Views init
        inputAge = findViewById(R.id.input_age);
        inputHeight = findViewById(R.id.input_height);
        inputWeight = findViewById(R.id.input_weight);
        inputBudget = findViewById(R.id.input_budget);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        spinnerMealPreference = findViewById(R.id.spinner_meal_preference);
        btnGetSuggestions = findViewById(R.id.btn_get_suggestions);

        // Spinner setup
        String[] mealPreferences = {"Vegetarian", "Non-Vegetarian", "Vegan", "Keto", "Paleo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mealPreferences);
        spinnerMealPreference.setAdapter(adapter);

        // Button click listener
        btnGetSuggestions.setOnClickListener(v -> {
            if (validateInputs()) {
                Intent intent = new Intent(MealPlannerActivity.this, SuggestionResultActivity.class);
                intent.putExtra("age", Integer.parseInt(inputAge.getText().toString().trim()));
                intent.putExtra("height", Float.parseFloat(inputHeight.getText().toString().trim()));
                intent.putExtra("weight", Float.parseFloat(inputWeight.getText().toString().trim()));
                intent.putExtra("budget", Float.parseFloat(inputBudget.getText().toString().trim()));

                int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
                RadioButton selectedGender = findViewById(selectedGenderId);
                intent.putExtra("gender", selectedGender != null ? selectedGender.getText().toString() : "");

                intent.putExtra("mealPreference", spinnerMealPreference.getSelectedItem().toString());

                startActivity(intent);
            }
        });
    }

    private boolean validateInputs() {
        if (inputAge.getText().toString().trim().isEmpty()) {
            inputAge.setError("Please enter your age");
            return false;
        }
        if (inputHeight.getText().toString().trim().isEmpty()) {
            inputHeight.setError("Please enter your height");
            return false;
        }
        if (inputWeight.getText().toString().trim().isEmpty()) {
            inputWeight.setError("Please enter your weight");
            return false;
        }
        if (inputBudget.getText().toString().trim().isEmpty()) {
            inputBudget.setError("Please enter your budget");
            return false;
        }
        if (radioGroupGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
