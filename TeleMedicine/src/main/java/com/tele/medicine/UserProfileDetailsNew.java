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
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Database on 9/12/2016.
 */
public class UserProfileDetailsNew extends AppCompatActivity implements View.OnClickListener{

    private EditText userName,firstResLine,secondResLine,firstOfficeLine,secondOfficeLine,UploadIdPhotoET,
                     uploadUserPhotoET;
    private Spinner genderSpinner,meritalStatus,countrySpinner;
    private CheckBox aadharCard,voterCard,passPort;
    private Button uploadIdPhotoButton,summitDetailsButton,editDetailsButton,addFamilyDetailsButton,viewFamilyDetailsButton,
            uploadUserPhotoButton;
    private TextView dateOfBirth;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    public int VAR;

    ArrayAdapter<String> mGenderAdapter,mMaritalStatusAdapter,countryAddapter;
    public String genderValue[] = {"Gender ", "Male ", "Female "};
    public String maritalValue[] = {"MaritalStatus","Single","Married"};
    public String countryValue[] = {"Slect Country","India","Japan","China"};

    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_details_new);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Personal Details");
        setSupportActionBar(toolbar);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        setUpIds();
        setAdapterValues();
        buttonClickListener();
        setDateTimeField();
        setClickEvent();

        /*Bundle bundle = getIntent().getExtras();
        String fromProfile = bundle.getString("FROMVIEWPROFILE");
        Log.e("PROFILE","profile..."+fromProfile);
        if(fromProfile.equals("fromviewprofile")){

            Log.e("VIEWPROFILE","profile...");
            summitDetailsButton.setVisibility(View.GONE);
            viewFamilyDetailsButton.setVisibility(View.VISIBLE);
        }else if(fromProfile.equals("fromupdateprofile")){

            Log.e("UPDATEPROFILE", "profile...");
            editableMethod();
            summitDetailsButton.setVisibility(View.VISIBLE);
            addFamilyDetailsButton.setVisibility(View.VISIBLE);

        }*/
    }

    private void setClickEvent() {
        uploadIdPhotoButton.setOnClickListener(this);
        uploadUserPhotoButton.setOnClickListener(this);

    }

    /*private void editableMethod() {
        userName.setEnabled(true);
        firstResLine.setEnabled(true);
        secondResLine.setEnabled(true);
        firstOfficeLine.setEnabled(true);
        secondOfficeLine.setEnabled(true);
        UploadIdPhotoET.setEnabled(true);

    }*/

    private void setUpIds() {
        /*edittext ids*/
        userName = (EditText)findViewById(R.id.userNameEt);
        dateOfBirth = (TextView)findViewById(R.id.dobEt);
      /*  dateOfBirth.setInputType(InputType.TYPE_NULL);
        dateOfBirth.requestFocus();*/
        firstResLine = (EditText)findViewById(R.id.firstResAddressEt);
        secondResLine = (EditText)findViewById(R.id.secondResAddressEt);
        firstOfficeLine = (EditText)findViewById(R.id.firstOfcAddressEt);
        secondOfficeLine = (EditText)findViewById(R.id.secondOfcAddressEt);
        UploadIdPhotoET = (EditText)findViewById(R.id.uploadIdPhotoETID);
        uploadUserPhotoET = (EditText)findViewById(R.id.uploadUserPhotoETID);

        /*spinner Ids*/
        genderSpinner = (Spinner)findViewById(R.id.spGender);
        meritalStatus = (Spinner)findViewById(R.id.spMaritalStatus);
        countrySpinner = (Spinner)findViewById(R.id.spCountry);

        /*checkbox ids*/
        aadharCard = (CheckBox)findViewById(R.id.adharCB);
        voterCard = (CheckBox)findViewById(R.id.voterCB);
        passPort = (CheckBox)findViewById(R.id.passportCB);

        /*button ids*/
        uploadIdPhotoButton = (Button)findViewById(R.id.uploadIdPhotoButtonID);
        uploadUserPhotoButton = (Button)findViewById(R.id.uploadUserPhotoButtonID);
        summitDetailsButton = (Button)findViewById(R.id.summitDetails);
        addFamilyDetailsButton = (Button)findViewById(R.id.addFamilyDetails);
        /*viewFamilyDetailsButton = (Button)findViewById(R.id.viewFamilyDetails);
        editDetailsButton = (Button)findViewById(R.id.editDetails);*/
    }

    private void setAdapterValues(){

        mGenderAdapter = new ArrayAdapter<String>(UserProfileDetailsNew.this,R.layout.single_row_spinner,genderValue);
        mGenderAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        genderSpinner.setAdapter(mGenderAdapter);

        mMaritalStatusAdapter = new ArrayAdapter<String>(UserProfileDetailsNew.this,R.layout.single_row_spinner,maritalValue);
        mMaritalStatusAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        meritalStatus.setAdapter(mMaritalStatusAdapter);

        countryAddapter = new ArrayAdapter<String>(UserProfileDetailsNew.this,R.layout.single_row_spinner,countryValue);
        countryAddapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        countrySpinner.setAdapter(countryAddapter);

    }

    private void buttonClickListener(){
        dateOfBirth.setOnClickListener(this);
//        viewFamilyDetailsButton.setOnClickListener(this);
        summitDetailsButton.setOnClickListener(this);
        addFamilyDetailsButton.setOnClickListener(this);

    }

    private void setDateTimeField(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(UserProfileDetailsNew.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateOfBirth.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addFamilyDetails:
            {
                Intent intent = new Intent(this,AddFamilyDetails.class);
                startActivity(intent);
                break;
            }

            case R.id.dobEt:
            {
                datePickerDialog.show();
                break;
            }
            case R.id.summitDetails:
            {
                Toast.makeText(this, "Submiting Details", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.uploadIdPhotoButtonID:
            {
                VAR = 1;
                selectImage();
                break;
            }
            case R.id.uploadUserPhotoButtonID:
            {
                VAR = 2;
                selectImage();
                break;
            }
        }
    }

    private void selectImage() {


        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileDetailsNew.this);
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
                        UploadIdPhotoET.setText(file.getName());
                    }else if(VAR==2) {
                        uploadUserPhotoET.setText(file.getName());
                    }
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
                if(VAR==1) {
                    UploadIdPhotoET.setText(f.getName());
                }else if(VAR==2) {
                    uploadUserPhotoET.setText(f.getName());
                }
                Log.w("path of image from gallery", picturePath + "");
//                upImage.setImageBitmap(thumbnail);
            }
        }
    }
}
