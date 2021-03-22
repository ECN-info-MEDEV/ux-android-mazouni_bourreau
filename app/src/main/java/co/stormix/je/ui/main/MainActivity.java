package co.stormix.je.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import co.stormix.je.R;
import co.stormix.je.data.model.Offer;
import co.stormix.je.ui.components.OfferRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

  }
    public void goToHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addOffer(View view) {
      Intent intent = new Intent(this, AddActivity.class);
      startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Log.d("MenuNav", String.valueOf(item.getItemId()));
        if (item.getItemId() == R.id.page_1) {
            goToHomePage();
            return true;
        }
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, "TODO", duration);
        toast.show();
        return true;
    }
}