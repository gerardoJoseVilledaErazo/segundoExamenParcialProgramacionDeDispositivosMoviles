package com.example.agendatelefonicave.daoVE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.agendatelefonicave.modelVE.ContactoVE;

import java.util.List;

@Dao
public interface ContactoDaoVE {
    @Query("select * from contactos")
    public List<ContactoVE> getAll();

    @Query("select * from contactos where id = :id")
    public ContactoVE get(int id);

    @Insert
    public void save(ContactoVE entity);

    @Delete
    public void delete(ContactoVE entity);

    @Update
    public void update(ContactoVE entity);
}
