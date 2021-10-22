package com.example.agendatelefonicave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.agendatelefonicave.adapterVE.ContactoAdapterVE;
import com.example.agendatelefonicave.daoVE.ContactoDaoImpRoomVE;
import com.example.agendatelefonicave.daoVE.ContactoDaoVE;
import com.example.agendatelefonicave.modelVE.ContactoVE;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ContactVE_ListActivity extends AppCompatActivity {

    FloatingActionButton btnNuevoContacto;
    RecyclerView rVwContactos;
    public static List<ContactoVE> contactoVEList;
    ContactoDaoVE contactoDaoVE;
    TextView txtNumberOfContacts;
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_ve_list);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        contactoDaoVE = new ContactoDaoImpRoomVE(getApplicationContext());

        //instancias a componentes graficos
        this.btnNuevoContacto =(FloatingActionButton)findViewById(R.id.btnNuevoContacto);
        this.rVwContactos =(RecyclerView) findViewById(R.id.rVwContactos);

        Intent intent = new Intent(this, NewVE_ContactActivity.class);

        // Eventos
        this.btnNuevoContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        // Cargando Datos
        this.cargarDatosVE();

        // Config Recycler View
        // Instanciando el AdapterVE
        ContactoAdapterVE contactoAdapterVE = new ContactoAdapterVE(this.contactoVEList,getApplicationContext(),contactoDaoVE);

        rVwContactos.setLayoutManager(new LinearLayoutManager(this));

        // Config del AdapterVE
        rVwContactos.setAdapter(contactoAdapterVE);
    }

    void cargarDatosVE(){
        contactoVEList = new ArrayList<ContactoVE>();
        contactoVEList = contactoDaoVE.getAll();
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