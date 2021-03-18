package co.stormix.je.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import co.stormix.je.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        String offerId = intent.getStringExtra("OfferId");

        // Get Offer by ID

        Log.d("DetailActivity", offerId);

    }
}