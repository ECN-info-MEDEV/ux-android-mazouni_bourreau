package co.stormix.je.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.DateFormat;
import java.util.Locale;

import co.stormix.je.R;
import co.stormix.je.data.model.Offer;

public class DetailsActivity extends AppCompatActivity {
  TextView pageTitle;
  TextView companyName;
  TextView clientName;
  TextView expiresAt;
  TextView createdAt;
  TextView description;
  TextView companySite;
  TextView companyType;
  ImageView imageView;

  Offer offer;
  private MainViewModel mViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);
    Intent intent = getIntent();
    String offerId = intent.getStringExtra("OfferId");
    mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

    // Get Offer by ID
    assert offerId != null;
    Log.d("DetailActivity", offerId);

    // Set text views
    pageTitle = (TextView) findViewById(R.id.detailsTitle);
    companyName = (TextView) findViewById(R.id.companyName);
    companySite = (TextView) findViewById(R.id.companyWebsite);
    companyType = (TextView) findViewById(R.id.companyType);
    clientName = (TextView) findViewById(R.id.clientName);
    expiresAt = (TextView) findViewById(R.id.expiresAt);
    createdAt = (TextView) findViewById(R.id.createdAt);
    description = (TextView) findViewById(R.id.projectDescription);
    imageView = (ImageView) findViewById(R.id.imageView2);

    //
    offer = mViewModel.getRepository().findById(offerId);

    Locale locale = new Locale("fr", "FR"); 
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);

    pageTitle.setText(offer.getCompany().getDisplayName());
    companyName.setText(offer.getCompany().getDisplayName());
    companySite.setText(offer.getCompany().getSite());
    companyType.setText(offer.getCompany().getCompanyType());
    clientName.setText(offer.getClient().getDisplayName());
    createdAt.setText(dateFormat.format(offer.getCreatedAt()));
    expiresAt.setText(dateFormat.format(offer.getExpiresAt()));
    description.setText(offer.getDescription());
    Picasso.get()
        .load(offer.getLogo())
        .resize(250, 250)
        .centerCrop()
        .into(imageView);

    Context context = getApplicationContext();
    int duration = Toast.LENGTH_SHORT;
    Toast toast = Toast.makeText(context, "Couldn't find offer.", duration);
    toast.show();
  }

  public void goToHomePage(){
    Intent intent = new Intent(this, MainActivity.class);
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