package com.example.rishika.finalproject_rishikaj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChallengeNoteActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference noteRef;

    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_PICK_PHOTO = 2;

    private ImageView imageView;
    private File photoFile;
    private Uri fileToUpload;
   // private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_note);

        database= FirebaseDatabase.getInstance();
        noteRef=database.getReference("note");

        imageView = (ImageView) findViewById(R.id.camera_image);
        //mStorageRef = FirebaseStorage.getInstance().getReference();

    }

    public void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
        }

        // Continue only if the File was successfully created
        if (photoFile != null) {
            Uri photoURI = FileProvider.getUriForFile(this,
                    "com.example.rishika.finalproject_rishikaj",
                    photoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(intent, REQUEST_TAKE_PHOTO);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat ("yyyyMMdd_HHmmss").format(new Date ());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }

    public void pickPhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_PICK_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;

        if (requestCode == REQUEST_TAKE_PHOTO) {
            fileToUpload = Uri.parse(photoFile.toURI().toString());
        }
        else if (requestCode == REQUEST_PICK_PHOTO) {
            fileToUpload=data.getData();
        }

        Picasso.with(this)
                .load(fileToUpload)
                .resize(500, 500)
                .centerInside()
                .into(imageView);
        Button upload = (Button) findViewById(R.id.upload);
        upload.setVisibility(View.VISIBLE);

    }

//    public void uploadPhoto(View view) {
//        if (fileToUpload != null) {
//            StorageReference uploadRef = mStorageRef.child("images/upload.jpg");
//
//            uploadRef.putFile(fileToUpload)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            @SuppressWarnings("VisibleForTests")
//                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                            Toast.makeText(ChallengeNoteActivity.this, "Upload successful.",Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener () {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(ChallengeNoteActivity.this, "Failed to upload.", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
//        else{
//            Toast.makeText(ChallengeNoteActivity.this, "No photo is selected.", Toast.LENGTH_SHORT).show();
//        }
//    }


    public void save(View view){
        TextView challengeTitle = (TextView) findViewById(R.id.challenge_title);
        EditText bodyField = (EditText) findViewById(R.id.body);
        long currentTime = Calendar.getInstance().getTimeInMillis();

        String title = challengeTitle.getText().toString();
        String body = bodyField.getText().toString();
        String time = String.valueOf(currentTime);

        Note note = new Note(title, body, time);

        noteRef.push().setValue(note);

        TextView statusView = (TextView) findViewById(R.id.display_status);
        statusView.setText(note.toString());

        statusView.setVisibility(View.VISIBLE);

        bodyField.setText("");
    }

}
