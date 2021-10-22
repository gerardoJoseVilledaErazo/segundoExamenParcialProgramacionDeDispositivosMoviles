package com.example.agendatelefonicave.daoVE;

import android.content.Context;

import androidx.room.Room;

import com.example.agendatelefonicave.DataBaseVE.DataBaseRoomVE;
import com.example.agendatelefonicave.modelVE.ContactoVE;

import java.util.List;

public class ContactoDaoImpRoomVE implements ContactoDaoVE{

    DataBaseRoomVE dataBaseRoomVE;
    ContactoDaoVE contactoDaoVE;

    public ContactoDaoImpRoomVE(Context context) {
        dataBaseRoomVE = Room.databaseBuilder(context,DataBaseRoomVE.class,"dbVE").allowMainThreadQueries().build();
        contactoDaoVE = dataBaseRoomVE.contactoDaoVE();
    }

    @Override
    public List<ContactoVE> getAll() {
        return contactoDaoVE.getAll();
    }

    @Override
    public ContactoVE get(int id) {
        return contactoDaoVE.get(id);
    }

    @Override
    public void save(ContactoVE entity) {
        contactoDaoVE.save(entity);
    }

    @Override
    public void delete(ContactoVE entity) {
        contactoDaoVE.delete(entity);
    }

    @Override
    public void update(ContactoVE entity) {
        contactoDaoVE.update(entity);
    }
}
