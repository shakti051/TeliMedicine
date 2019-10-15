package com.tele.medicine;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Database on 9/13/2016.
 */
public class AddFamilyDetails extends AppCompatActivity implements View.OnClickListener {

    ArrayAdapter<String> mGenderAdapter, mMaritalStatusAdapter, mRelationShipAdapter, mCountryAdapter;
    public String genderValue[] = {"Gender", "Male", "Female"};
    ;
    public String maritalValue[] = {"MaritalStatus", "Single", "Married"};
    public String relationValue[] = {"Relationship", "Father ", "Brother ", "Sister ", "Uncle "};
    public String countryValue[] = {"Slect Country", "India", "Japan", "China"};

    private CheckBox addressCB;

    Spinner spRelationShip, spGender, spMaritalStatus, spCountry;
    private EditText firstResAddressEt, secondResAddressEt,slectIdImageEditText,uploadMemPhotoET;
    private Button slectIdImgBtn,submitBtn,selectMemPhotoButton;
    private static int IMG_RESULT = 1;
    private ImageView upImage;
    String ImageDecode;
    public int VAR;
    private TextView dateOfBirth;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_family_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Member Details");
        setSupportActionBar(toolbar);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        setUpIds();
        setAdapterValuse();
        setClickEvent();
        setDateTimeField();
    }

    public void setUpIds() {
        spRelationShip = (Spinner) findViewById(R.id.spRelationShipID);
        spGender = (Spinner) findViewById(R.id.spGenderID);
        spMaritalStatus = (Spinner) findViewById(R.id.spMaritalStatusID);
        spCountry = (Spinner) findViewById(R.id.spCountry);
        addressCB = (CheckBox) findViewById(R.id.addressCB);
        firstResAddressEt = (EditText) findViewById(R.id.firstResAddressEt);
        secondResAddressEt = (EditText) findViewById(R.id.secondResAddressEt);
        slectIdImageEditText =(EditText)findViewById(R.id.uploadIdPhotoETID);
        uploadMemPhotoET = (EditText)findViewById(R.id.uploadMemPhotoETID);
        slectIdImgBtn = (Button) findViewById(R.id.uploadIdPhotoButtonID);
        submitBtn =(Button)findViewById(R.id.summitDetails);
        selectMemPhotoButton = (Button)findViewById(R.id.uploadMemPhotoButtonID);

        dateOfBirth = (TextView)findViewById(R.id.dobtvID);
//        upImage = (ImageView) findViewById(R.id.upImageID);
    }

    public void setAdapterValuse() {
        mGenderAdapter = new ArrayAdapter<String>(this, R.layout.single_row_spinner, genderValue);
        mGenderAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        spGender.setAdapter(mGenderAdapter);

        mMaritalStatusAdapter = new ArrayAdapter<String>(this, R.layout.single_row_spinner, maritalValue);
        mMaritalStatusAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        spMaritalStatus.setAdapter(mMaritalStatusAdapter);

        mRelationShipAdapter = new ArrayAdapter<String>(this, R.layout.single_row_spinner, relationValue);
        mRelationShipAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        spRelationShip.setAdapter(mRelationShipAdapter);

        mCountryAdapter = new ArrayAdapter<String>(this, R.layout.single_row_spinner, countryValue);
        mRelationShipAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        spCountry.setAdapter(mCountryAdapter);
    }

    private void setDateTimeField(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(AddFamilyDetails.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateOfBirth.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    public void setClickEvent() {

        addressCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    firstResAddressEt.setEnabled(false);
                    secondResAddressEt.setEnabled(false);
                } else {
                    firstResAddressEt.setEnabled(true);
                    secondResAddressEt.setEnabled(true);
                }
            }
        });

        /*slectImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, IMG_RESULT);*//*
                selectImage();
            }
        });*/
        slectIdImgBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        selectMemPhotoButton.setOnClickListener(this);
        dateOfBirth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        /*if(v == slectImgBtn){
            selectImage();
        }else if(v==uploadImgBtn){

            Toast.makeText(this,"Uploading...",Toast.LENGTH_LONG).show();
        }*/
        switch (v.getId()){
            case R.id.uploadIdPhotoButtonID:
            {
                VAR = 1;
                selectImage();
                break;
            }
            case R.id.uploadMemPhotoButtonID:
            {
                VAR = 2;
                selectImage();
                break;
            }
            case R.id.dobtvID:{
                datePickerDialog.show();
                break;
            }
        }

    }


    private void selectImage() {


        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(AddFamilyDetails.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

//                    upImage.setImageBitmap(bitmap);


                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    file.getName();
                    Log.e("CAMERAIMAGENAME", "cameraimagename... " + file.getName());

                    if(VAR==1) {
                        slectIdImageEditText.setText(file.getName());
                    }else if(VAR==2) {
                        uploadMemPhotoET.setText(file.getName());
                    }
//                    slectImageEditText.setText(file.getName());
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                String string = MediaStore.Images.Media.DISPLAY_NAME;
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);

                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));

                File f = new File(picturePath);
                f.getName();
                Log.e("GALLERYIMAGENAME", "galleryimagename... " + f.getName());
//                slectImageEditText.setText(f.getName());
                if(VAR==1) {
                    slectIdImageEditText.setText(f.getName());
                }else if(VAR==2) {
                    uploadMemPhotoET.setText(f.getName());
                }
                Log.w("path of image from gallery", picturePath + "");
//                upImage.setImageBitmap(thumbnail);
            }
        }
    }



    /*public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }*/
}