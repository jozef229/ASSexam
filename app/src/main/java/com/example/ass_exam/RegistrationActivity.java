package com.example.ass_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class RegistrationActivity extends AppCompatActivity {

    EditText editTextPersonEmail;
    EditText editTextPersonName;
    EditText editTextPersonSurname;
    Spinner spinnergGenderArrays;
    Spinner spinnergHandArrays;
    Spinner spinnergHandTypeArrays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ShareViaEmail("logs", "custom.csv");

        editTextPersonEmail = findViewById(R.id.editTextPersonEmail);
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextPersonSurname = findViewById(R.id.editTextPersonSurname);
        final Button buttonRegistration = findViewById(R.id.buttonRegistration);

        spinnergGenderArrays = findViewById(R.id.spinnerGenderArrays);
        spinnergHandArrays = findViewById(R.id.spinnerHandArrays);
        spinnergHandTypeArrays = findViewById(R.id.spinnerHandTypeArrays);


        spinnergHandArrays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                System.out.println("bbb");
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnergHandArrays.getSelectedItemPosition() == 0) {
                    TextView textView = (TextView) parent.getChildAt(0);
                    textView.setTextColor(getColor(R.color.colorGray));
                }
                else {
                    TextView textView = (TextView) parent.getChildAt(0);
                    textView.setTextColor(getColor(R.color.colorBlack));
                }
                System.out.println(spinnergHandArrays.getSelectedItemPosition());
            }
        });

        spinnergHandTypeArrays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                System.out.println("bbb");
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnergHandTypeArrays.getSelectedItemPosition() == 0) {
                    TextView textView = (TextView) parent.getChildAt(0);
                    textView.setTextColor(getColor(R.color.colorGray));
                }
                else {
                    TextView textView = (TextView) parent.getChildAt(0);
                    textView.setTextColor(getColor(R.color.colorBlack));
                }
                System.out.println(spinnergHandTypeArrays.getSelectedItemPosition());
            }
        });

        spinnergGenderArrays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                System.out.println("bbb");
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnergGenderArrays.getSelectedItemPosition() == 0) {
                    TextView textView = (TextView) parent.getChildAt(0);
                    textView.setTextColor(getColor(R.color.colorGray));
                }
                else {
                    TextView textView = (TextView) parent.getChildAt(0);
                    textView.setTextColor(getColor(R.color.colorBlack));
                }
                System.out.println(spinnergHandTypeArrays.getSelectedItemPosition());
            }
        });

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(checkDataEntered()){
                    Intent intent = new Intent(RegistrationActivity.this, TestActivity.class);
                    intent.putExtra("PERSON_GENDER", spinnergGenderArrays.getSelectedItem().toString());
                    intent.putExtra("PERSON_HAND_TYPE", spinnergHandTypeArrays.getSelectedItem().toString());
                    intent.putExtra("PERSON_HAND", spinnergHandArrays.getSelectedItem().toString());
                    intent.putExtra("PERSON_EMAIL", editTextPersonEmail.getText().toString());
                    intent.putExtra("PERSON_NAME", editTextPersonName.getText().toString());
                    intent.putExtra("PERSON_SURNAME", editTextPersonSurname.getText().toString());
                    startActivity(intent);
//                    finish();
                }
            }
        });

    }




//    String filename = "custom.csv";
//    //        File filelocation = new File(Environment.getDataDirectory().getAbsolutePath() + "/logs/", filename);
//    File filelocation = new File(getApplicationContext().getFilesDir() + "/logs/" + filename);
//    Uri path = Uri.fromFile(filelocation);
//    Intent emailIntent = new Intent(Intent.ACTION_SEND);
//        emailIntent .setType("vnd.android.cursor.dir/email");
//    String to[] = {"varga.jozef@icloud.com"};
//        emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
//        emailIntent .putExtra(Intent.EXTRA_STREAM, path);
//        emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Subject");
//    startActivity(Intent.createChooser(emailIntent , "Send email..."));


    private void ShareViaEmail(String folder_name, String file_name) {
        try {
            File root= getApplicationContext().getFilesDir();
            String filelocation= root.getAbsolutePath() + folder_name + "/" + file_name;
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
            String message="File to be shared is " + file_name + ".";
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse( "file://"+filelocation));
            intent.putExtra(Intent.EXTRA_TEXT, message);
            intent.setData(Uri.parse("mailto:varga.jozef@icloud.com"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        } catch(Exception e)  {
            System.out.println("is exception raises during sending mail"+e);
        }
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered() {
        boolean validation = true;


        if (isEmpty(editTextPersonName)) {
            Toast t = Toast.makeText(this, "You must enter name to register!", Toast.LENGTH_SHORT);
            t.show();
            validation = false;
        }
        else if (isEmpty(editTextPersonSurname)) {
            Toast t = Toast.makeText(this, "You must enter surname to register!", Toast.LENGTH_SHORT);
            t.show();
            validation = false;
        }
        else if (!isEmail(editTextPersonEmail)) {
            Toast t = Toast.makeText(this, "You must enter email to register!", Toast.LENGTH_SHORT);
            t.show();
            validation = false;
        }
        else if (spinnergGenderArrays.getSelectedItemPosition() == 0){
            Toast t = Toast.makeText(this, "You must choice gender to register!", Toast.LENGTH_SHORT);
            t.show();
            validation = false;
        }
        else if (spinnergHandArrays.getSelectedItemPosition() == 0){
            Toast t = Toast.makeText(this, "You must choice hand to register!", Toast.LENGTH_SHORT);
            t.show();
            validation = false;
        }
        else if (spinnergHandTypeArrays.getSelectedItemPosition() == 0){
            Toast t = Toast.makeText(this, "You must choice hand type to register!", Toast.LENGTH_SHORT);
            t.show();
            validation = false;
        }

        System.out.println(spinnergGenderArrays.getSelectedItem());
        return validation;

    }
}