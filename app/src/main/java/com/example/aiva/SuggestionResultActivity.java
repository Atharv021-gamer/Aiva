package com.example.aiva;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SuggestionResultActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_result);

        ivBack = findViewById(R.id.ivBack);
        tvResults = findViewById(R.id.tvResults);

        // Back button click
        ivBack.setOnClickListener(v -> {
            Intent intent = new Intent(SuggestionResultActivity.this, MealPlannerActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Get values from intent
        int age = getIntent().getIntExtra("age", 0);
        float height = getIntent().getFloatExtra("height", 0);
        float weight = getIntent().getFloatExtra("weight", 0);
        float budget = getIntent().getFloatExtra("budget", 0);
        String gender = getIntent().getStringExtra("gender");
        String mealPreference = getIntent().getStringExtra("mealPreference");

        // Calculate BMI
        float heightInMeters = height / 100;
        float bmi = weight / (heightInMeters * heightInMeters);

        // Calculate BMR
        double bmr;
        if ("Male".equalsIgnoreCase(gender)) {
            bmr = 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            bmr = 10 * weight + 6.25 * height - 5 * age - 161;
        }
        double dailyCalories = bmr * 1.55;

        // Macro breakdown
        double carbs = (dailyCalories * 0.4) / 4;
        double protein = (dailyCalories * 0.3) / 4;
        double fats = (dailyCalories * 0.3) / 9;

        // Get meal plan
        String[][] plan = getMealPlan(mealPreference);
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        // Build formatted HTML output
        StringBuilder html = new StringBuilder();
        html.append("<h2 style='text-align:center;'>🍽 AI 7-Day Personalized Meal Plan 🍽</h2>")
                .append("<p><b>Age:</b> ").append(age)
                .append(" | <b>Gender:</b> ").append(gender)
                .append(" | <b>Height:</b> ").append(height).append(" cm")
                .append(" | <b>Weight:</b> ").append(weight).append(" kg")
                .append("<br><b>BMI:</b> ").append(String.format("%.1f", bmi)).append(" (").append(getBmiCategory(bmi)).append(")")
                .append("<br><b>Calories/day:</b> ").append(String.format("%.0f", dailyCalories))
                .append("<br><b>Macros:</b> 🥖 ").append(String.format("%.0f", carbs)).append("g carbs | 🍗 ").append(String.format("%.0f", protein)).append("g protein | 🥑 ").append(String.format("%.0f", fats)).append("g fats</p>")
                .append("<hr>");

        // Day-wise plan
        for (int i = 0; i < 7; i++) {
            html.append("<h3>📅 ").append(days[i]).append("</h3>")
                    .append("<b>7:30 AM — Breakfast:</b> ").append(plan[0][i]).append("<br>")
                    .append("<b>10:00 AM — Snack:</b> ").append(plan[1][i]).append("<br>")
                    .append("<b>1:00 PM — Lunch:</b> ").append(plan[2][i]).append("<br>")
                    .append("<b>4:00 PM — Evening:</b> ").append(plan[3][i]).append("<br>")
                    .append("<b>7:30 PM — Dinner:</b> ").append(plan[4][i]).append("<br>")
                    .append("<b>9:30 PM — Late Snack:</b> ").append(plan[5][i]).append("<br><br>");
        }

        html.append("<hr>")
                .append("<h4>💡 Tips for Best Results:</h4>")
                .append("• Drink 2–3L water/day 💧<br>")
                .append("• Sleep 7–8 hours 😴<br>")
                .append("• Exercise 3x/week 🏋️‍♂️<br>")
                .append("• Include veggies & protein in each meal 🥦🍗");

        // Set HTML text
        tvResults.setText(Html.fromHtml(html.toString(), Html.FROM_HTML_MODE_LEGACY));
    }

    private String[][] getMealPlan(String preference) {
        // Example vegetarian plan
        String[][] vegetarian = {
                {"Oats & banana 🍌", "Poha", "Idli sambar", "Upma", "Smoothie bowl", "Paneer toast", "Veg paratha"},
                {"Apple 🍎", "Papaya", "Banana", "Mixed nuts", "Orange", "Grapes", "Melon"},
                {"Dal & rice", "Chole roti", "Veg pulao", "Sambar rice", "Rajma chawal", "Veg biryani", "Lentil soup"},
                {"Yogurt", "Peanuts", "Fruit salad", "Corn chaat", "Sprouts", "Lassi", "Trail mix"},
                {"Paneer curry", "Veg stew", "Stuffed capsicum", "Dal fry", "Mix veg curry", "Veg cutlets", "Palak paneer"},
                {"Milk 🥛", "Walnuts", "Almond milk", "Fruit", "Herbal tea", "Peanut butter toast", "Milk 🥛"}
        };
        return vegetarian;
    }

    private String getBmiCategory(float bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }
}
