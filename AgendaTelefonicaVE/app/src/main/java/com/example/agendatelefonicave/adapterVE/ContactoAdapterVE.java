package com.example.agendatelefonicave.adapterVE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendatelefonicave.NewVE_ContactActivity;
import com.example.agendatelefonicave.R;
import com.example.agendatelefonicave.daoVE.ContactoDaoVE;
import com.example.agendatelefonicave.modelVE.ContactoVE;

import java.util.List;

public class ContactoAdapterVE extends RecyclerView.Adapter<ContactoAdapterVE.ViewHolder> {
    List<ContactoVE> contactos;
    Context context; // Para saber de donde viene la llamada de esa accion
    ContactoDaoVE daoVE;

    //constructor del adapter
    public ContactoAdapterVE(List<ContactoVE> contactos, Context context, ContactoDaoVE daoVE) {
        this.contactos = contactos;
        this.context = context;
        this.daoVE = daoVE;
    }

    // Manipulacion de vista(xml)
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtId;
        TextView txtNombre;
        TextView txtNumero;
        TextView txtPropietario;
        Button btnModificar;
        Button btnEliminar;
        Context context;

        public ViewHolder(/*@NonNull @org.jetbrains.annotations.NotNull*/
                View itemView,
                Context context
        ) {
            super(itemView);

            txtId=(TextView) itemView.findViewById(R.id.txtId);
            txtNombre=(TextView) itemView.findViewById(R.id.txtNombre);
            txtNumero=(TextView) itemView.findViewById(R.id.txtNumero);
            txtPropietario=(TextView) itemView.findViewById(R.id.txtPropietario);
            btnModificar=(Button) itemView.findViewById(R.id.btnModificar);
            btnEliminar=(Button) itemView.findViewById(R.id.btnEliminar);

            this.context=context;
        }
    }

    // Configuraciones
    @NonNull     /*@org.jetbrains.annotations.NotNull*/
    @Override
    public ContactoAdapterVE.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto_ve,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoAdapterVE.ViewHolder holder, int position ) {
        ContactoVE contactoVE = contactos.get(position );
        holder.txtId.setText("" + contactoVE.getId() );
        holder.txtNombre.setText(contactoVE.getNombre() );
        holder.txtNumero.setText(contactoVE.getNumero() );
        holder.txtPropietario.setText(contactoVE.getPropietario() );

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoVE.delete(contactoVE);
                contactos =daoVE.getAll();
                notifyDataSetChanged();
                Toast.makeText(context.getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, NewVE_ContactActivity.class);
                intent.putExtra("estado",1); // 1 : Update
                intent.putExtra("contacto",contactoVE);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.contactos.size();
    }
}
