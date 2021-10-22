package com.example.agendatelefonicave;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendatelefonicave.daoVE.ContactoDaoImpRoomVE;
import com.example.agendatelefonicave.daoVE.ContactoDaoVE;
import com.example.agendatelefonicave.modelVE.ContactoVE;
import com.example.agendatelefonicave.utilsVE.UtilsVE;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class NewVE_ContactActivity extends AppCompatActivity {

    TextView txtPropietario, txtNewContact;
    // User Name
    private TextInputLayout contactNameTextInput;
    private TextInputEditText contactNameEditText;
    // Phone Number
    private TextInputLayout contactPhoneTextInput;
    private TextInputEditText contactPhoneEditText;
    // Next Button
    MaterialButton saveButton;
    MaterialButton backButton;

    ContactoDaoVE contactoDaoVE; // No se recomienda crear objetos de la clase SINO de la interface
    ContactoVE contactoVE;
    int estado;

    public static SharedPreferences sharedPreferences;
    public static String NAME_FILE = "configuration";
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ve_contact);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        estado=0;
        contactoDaoVE = new ContactoDaoImpRoomVE(getApplicationContext() );
        // Instancias a los componentes graficos **********************************************
        // contact Name
        this.contactNameTextInput = (TextInputLayout ) findViewById(R.id.contact_name_text_input_layout);
        this.contactNameEditText = (TextInputEditText ) findViewById(R.id.contact_name_edit_text);
        // contact Phone Number
        this.contactPhoneTextInput = (TextInputLayout ) findViewById(R.id.contact_phone_text_input_layout);
        this.contactPhoneEditText = (TextInputEditText ) findViewById(R.id.contact_phone_edit_text);
        // Save Button
        this.saveButton = (MaterialButton ) findViewById(R.id.btnGuardarContacto);
        this.backButton = (MaterialButton) findViewById(R.id.back_button);
        this.txtNewContact = (TextView) findViewById(R.id.txtNewContact);

        this.txtPropietario = (TextView) findViewById(R.id.txtPropietario);
        sharedPreferences = getSharedPreferences(NAME_FILE,MODE_PRIVATE);
        user = sharedPreferences.getString("USER", "");

        Intent intent = new Intent(this, ContactVE_ListActivity.class);
        cargarVE();

        // Eventos
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetVE();
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isValid(contactNameEditText.getText()) && !isValid(contactPhoneEditText.getText())){
                    contactNameTextInput.setError("Required");
                    contactPhoneTextInput.setError("Required");
                }else{
                    if(UtilsVE.verifyEditText(contactNameEditText)&& UtilsVE.verifyEditTextNumber(contactPhoneEditText)  ) {

                        if(estado == 0){
                            guardarVE();
                            Toast.makeText(NewVE_ContactActivity.this, "Saved.", Toast.LENGTH_SHORT).show();
                        }else{
                            actualizarVE();
                            Toast.makeText(NewVE_ContactActivity.this, "Updated.", Toast.LENGTH_SHORT).show();
                        }
                        resetVE();
                        startActivity(intent);
                    }
                }
            }
        });
    }

    void guardarVE(){
        contactoVE = new ContactoVE();
        contactoVE.setNombre(contactNameEditText.getText().toString());
        contactoVE.setNumero(contactPhoneEditText.getText().toString());
        contactoVE.setPropietario(user);
        contactoDaoVE.save(contactoVE);
    }

    void actualizarVE(){
        contactoVE.setNombre(contactNameEditText.getText().toString());
        contactoVE.setNumero(contactPhoneEditText.getText().toString());
        contactoVE.setPropietario(user);

        contactoDaoVE.update(contactoVE);
    }

    void resetVE(){
        contactNameEditText.setText("");
        contactPhoneEditText.setText("");
        saveButton.setText("Save");
    }

    void cargarVE(){
        try {
            Intent intent = getIntent();
            contactoVE = (ContactoVE) intent.getSerializableExtra("contacto");
            estado = intent.getIntExtra("estado",0);
            contactNameEditText.setText(contactoVE.getNombre());
            contactPhoneEditText.setText(contactoVE.getNumero());
            saveButton.setText("Update");
            txtNewContact.setText("Contact to Update");
        }catch (Exception e){
            estado=0;
        }
    }

    //Al dar clic en el botón “Cancelar”
    // debe borrar el texto o valor de todos los componentes.
    public void Cancel(View view) {
        contactNameEditText.setText("");
        contactPhoneEditText.setText("");
    }

    private boolean isValid(@Nullable Editable text) {
        return text != null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}