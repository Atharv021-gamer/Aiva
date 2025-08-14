package com.example.aiva;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SkinCareProductsActivity extends AppCompatActivity {

    private ImageView ivBack;
    private GridLayout gridProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_care_products);

        ivBack = findViewById(R.id.ivBack);
        gridProducts = findViewById(R.id.gridProducts);

        ivBack.setOnClickListener(v -> finish());

        // Example Premium Product Data (name, price, suitability, image)
        Object[][] products = {
                {"Hydrating Face Wash", "₹299", "Dry Skin", R.drawable.facewash},
                {"Oil Control Moisturizer", "₹499", "Oily Skin", R.drawable.moisturizer},
                {"Vitamin C Serum", "₹899", "Dull Skin", R.drawable.serum},
                {"SPF 50 Sunscreen", "₹699", "All Skin Types", R.drawable.sunscreen},
                {"Aloe Gel", "₹249", "Sensitive Skin", R.drawable.aloe},
                {"Charcoal Face Mask", "₹399", "Deep Clean", R.drawable.charcoal}
        };

        for (Object[] product : products) {
            addProductCard((String) product[0], (String) product[1], (String) product[2], (int) product[3]);
        }
    }

    private void addProductCard(String name, String price, String suitability, int imageRes) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(20, 20, 20, 20);
        card.setBackgroundResource(R.drawable.bg_card_purple);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(12, 12, 12, 12);
        card.setLayoutParams(params);

        ImageView img = new ImageView(this);
        img.setImageResource(imageRes);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 250);
        img.setLayoutParams(imgParams);

        TextView tvName = new TextView(this);
        tvName.setText(name);
        tvName.setTextSize(16f);
        tvName.setTextColor(getResources().getColor(android.R.color.white));
        tvName.setTypeface(null, android.graphics.Typeface.BOLD);

        TextView tvPrice = new TextView(this);
        tvPrice.setText(price);
        tvPrice.setTextSize(14f);
        tvPrice.setTextColor(getResources().getColor(android.R.color.holo_green_light));

        TextView tvSuitability = new TextView(this);
        tvSuitability.setText("For: " + suitability);
        tvSuitability.setTextSize(12f);
        tvSuitability.setTextColor(getResources().getColor(android.R.color.white));
        tvSuitability.setGravity(Gravity.START);

        card.addView(img);
        card.addView(tvName);
        card.addView(tvPrice);
        card.addView(tvSuitability);

        gridProducts.addView(card);
    }
}
