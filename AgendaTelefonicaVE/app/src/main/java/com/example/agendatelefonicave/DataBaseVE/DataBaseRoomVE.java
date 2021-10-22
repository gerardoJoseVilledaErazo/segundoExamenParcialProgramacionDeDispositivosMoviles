package com.example.agendatelefonicave.DataBaseVE;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.agendatelefonicave.daoVE.ContactoDaoVE;
import com.example.agendatelefonicave.modelVE.ContactoVE;

@Database(entities = {ContactoVE.class}, version = 1)
public abstract class DataBaseRoomVE extends RoomDatabase {
    public abstract ContactoDaoVE contactoDaoVE();
}
