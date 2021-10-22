package com.example.agendatelefonicave;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.example.agendatelefonicave.utilsVE.UtilsVE;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainVEActivity extends AppCompatActivity {

        // User Name
        private TextInputLayout userNameTextInput;
        private TextInputEditText userNameEditText;
        // Phone Number
        private TextInputLayout phoneNumberTextInput;
        private TextInputEditText phoneNumberEditText;
        // Next Button
        MaterialButton nextButton;
        //SharedPreferences
        public static SharedPreferences sharedPreferences;
        public static String NAME_FILE = "configuration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainve);

        //edtUserName = (EditText) findViewById(R.id.user_name_edit_text);
        //edtPhoneNumber = (EditText) findViewById(R.id.phone_number_edit_text);
        //btnNext = (Button) findViewById(R.id.next_button);

        // User Name
        userNameTextInput = (TextInputLayout ) findViewById(R.id.user_name_text_input_layout);
        userNameEditText = (TextInputEditText ) findViewById(R.id.user_name_edit_text);

        // Phone Number
        phoneNumberTextInput = (TextInputLayout ) findViewById(R.id.phone_number_text_input_layout);
        phoneNumberEditText = (TextInputEditText ) findViewById(R.id.phone_number_edit_text);

        // Next Button
        nextButton = (MaterialButton ) findViewById(R.id.next_button);

        nextButton.setOnClickListener(view -> {
            addUser();
        });
    }

    private void addUser(){
        if(!isValid(userNameEditText.getText()) && !isValid(phoneNumberEditText.getText())){
            userNameTextInput.setError("Required");
            phoneNumberTextInput.setError("Required");
        }else{
            if(UtilsVE.verifyEditText(userNameEditText)&& UtilsVE.verifyEditTextNumber(phoneNumberEditText)  ) {
                // Fetching the stored data
                // from the SharedPreference
                sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
                SharedPreferences.Editor editorConfig = sharedPreferences.edit();
                editorConfig.putString("USER", userNameEditText.getText().toString() );
                editorConfig.putString("PHN", phoneNumberEditText.getText().toString() );
                editorConfig.commit();
                Intent intent = new Intent(this, ContactVE_ListActivity.class);
                startActivity(intent); // Inicia Activity
                String user = sharedPreferences.getString("USER", "");
                Toast.makeText(
                        MainVEActivity.this,
                        "Hello "+user+"!",
                        Toast.LENGTH_SHORT
                ).show();
            }
            /*
            startActivity(new Intent(MainVEActivity.this,
                    MainActivity.class));*/
        }
    }

    //Al dar clic en el botón “Cancelar”
    // debe borrar el texto o valor de todos los componentes.
    public void Cancel(View view) {
        userNameEditText.setText("");
        phoneNumberEditText.setText("");
    }

    private boolean isValid(@Nullable Editable text) {
        return text != null;
    }

    // Fetch the stored data in onResume()
    // Because this is what will be called
    // when the app opens again
    @Override
    protected void onResume() {
        super.onResume();

        // Fetching the stored data
        // from the SharedPreference
        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);

        String user = sharedPreferences.getString("USER", "");
        String phn = sharedPreferences.getString("PHN", "");

        // Setting the fetched data
        // in the EditTexts
        userNameEditText.setText(user);
        phoneNumberEditText.setText(phn);
    }

    // Store the data in the SharedPreference
    // in the onPause() method
    // When the user closes the application
    // onPause() will be called
    // and data will be stored
    @Override
    protected void onPause() {
        super.onPause();

        // Creating a shared pref object
        // with a file name "configuration"
        // in private mode

        sharedPreferences = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editorConfig = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply

        editorConfig.putString("USER", userNameEditText.getText().toString() );
        editorConfig.putString("PHN", phoneNumberEditText.getText().toString() );
        editorConfig.apply();
    }
}