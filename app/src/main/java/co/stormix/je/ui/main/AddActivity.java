package co.stormix.je.ui.main;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import co.stormix.je.R;
import java.util.UUID;

public class AddActivity extends AppCompatActivity {
  //storage permission code
  private static final int STORAGE_PERMISSION_CODE = 123;
  Calendar myCalendar;
  DatePickerDialog.OnDateSetListener date;
  EditText dateInput;
  //Image request code
  private int PICK_IMAGE_REQUEST = 1;
  //Bitmap to get image from gallery
  private Bitmap bitmap;

  //Uri to store the image uri
  private Uri filePath;
  private ImageView imageView;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add);

    imageView = (ImageView) findViewById(R.id.addLogo);
    myCalendar = Calendar.getInstance();

    dateInput = (EditText) findViewById(R.id.addExpiresAt);
    date = new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // TODO Auto-generated method stub
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, month);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
      }

    };

    dateInput.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // TODO Auto-generated method stub
        new DatePickerDialog(AddActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
      }
    });
  }

  private void updateLabel() {
    String myFormat = "dd-MM-YYYY"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    dateInput.setText(sdf.format(myCalendar.getTime()));
  }

  //Requesting permission
  private void requestStoragePermission() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
      return;

    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
      //If the user has denied the permission previously your code will come to this block
      //Here you can explain why you need this permission
      //Explain here why you need this permission
    }
    //And finally ask for the permission
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
  }

  //This method will be called when the user will tap on allow or deny
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    //Checking the request code of our request
    if (requestCode == STORAGE_PERMISSION_CODE) {

      //If permission is granted
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        //Displaying a toast
        Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
      } else {
        //Displaying another toast if permission is not granted
        Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
      }
    }
  }
  /*
   * This is the method responsible for image upload
   * We need the full image path and the name for the image in this method
   * */
  public void uploadMultipart() {
    //getting the actual path of the image
    String path = getPath(filePath);

    //Uploading code
    try {
      String uploadId = UUID.randomUUID().toString();

      //Creating a multi part request
      new MultipartUploadRequest(this, uploadId, "todo")
          .addFileToUpload(path, "image") //Adding file
          .addParameter("name", "Image name") //Adding text parameter to the request
          .setNotificationConfig(new UploadNotificationConfig())
          .setMaxRetries(2)
          .startUpload(); //Starting the upload

    } catch (Exception exc) {
      Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
    }
  }


  //method to show file chooser
  private void showFileChooser() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
  }

  //handling the image chooser activity result
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
      filePath = data.getData();
      try {
        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
        imageView.setImageBitmap(bitmap);

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  //method to get the file path from uri
  public String getPath(Uri uri) {
    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
    assert cursor != null;
    cursor.moveToFirst();
    String document_id = cursor.getString(0);
    document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
    cursor.close();

    cursor = getContentResolver().query(
        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
    assert cursor != null;
    cursor.moveToFirst();
    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
    cursor.close();

    return path;
  }

  public void openUpload(View v) {
    //Requesting storage permission
    requestStoragePermission();
    showFileChooser();
  }

  public void notImplemented(View v) {
    int duration = Toast.LENGTH_SHORT;
    Toast toast = Toast.makeText(this, "Not implemented", duration);
    toast.show();
  }

  public void goToHomePage() {
    super.onBackPressed();
  }
}