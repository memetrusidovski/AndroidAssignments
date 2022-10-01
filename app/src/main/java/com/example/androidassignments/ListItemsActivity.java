package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

public class ListItemsActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "ListActivity";
    private static final int TAKE_PICTURE = 0;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "In onCreate()");
    }
    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = "";// new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(""));
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",         /* suffix */
            storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();

        return image;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                    "com.mydomain.fileprovider",
                    photoFile);
                imageUri = photoURI;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                Log.i("camera", "worked til here");
                startActivityForResult(takePictureIntent, this.TAKE_PICTURE);
            }
        }else Log.i("Function", " FAILED");
    }

    public void openCamera(View v){
        Intent cameraIntent = new Intent( "android.media.action.IMAGE_CAPTURE");
        /*cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        File photo = new File(this.getFilesDir(),  "Pic.jpg");
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));

        //imageUri = Uri.fromFile(photo);
        imageUri = FileProvider.getUriForFile(this, "com.mydomain.fileprovider", photo);
*/
        dispatchTakePictureIntent();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == this.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    ContentResolver cr = getContentResolver();
                    getContentResolver().notifyChange(selectedImage, null);
                    ImageButton imageView = (ImageButton) findViewById(R.id.imageButton);

                    Bitmap bitmap;
                    try {
                        bitmap = MediaStore.Images.Media
                            .getBitmap(this.getContentResolver(), selectedImage);

                        Log.i("URI", bitmap.toString());
                        imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 70,70, false));
                        Toast.makeText(this, selectedImage.toString(),
                            Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                            .show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }

    public void setOnCheckedChanged(View v){
        Switch sw = (Switch) findViewById(R.id.switch3);

        if(sw.isChecked())
            Toast.makeText(this, "Switch is On", Toast.LENGTH_SHORT)
                .show();
        else
            Toast.makeText(this, "Switch is Off", Toast.LENGTH_SHORT)
                .show();
    }

    public void toaster(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT)
            .show();
    }

    public void OnCheckChanged(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml

            .setTitle(R.string.dialog_title)
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    Intent resultIntent = new Intent(  );
                    resultIntent.putExtra("Response", "Here is my response");
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            })
            .show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
