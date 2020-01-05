package com.example.ass_exam;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



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